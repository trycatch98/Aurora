/*
 * Copyright (c) 2024 trycatch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.trycatch.crypto

import com.solana.vendor.TweetNaclFast
import com.solana.vendor.bip32.wallet.DerivableType
import com.solana.vendor.bip32.wallet.SolanaBip44
import com.trycatch.crypto.model.getBase58PrivateKey
import com.trycatch.crypto.model.getBase58PublicKey
import com.trycatch.domain.WalletGenerator
import com.trycatch.domain.model.Wallet
import org.bitcoinj.crypto.MnemonicCode
import javax.inject.Inject

class WalletGeneratorImpl @Inject constructor(): WalletGenerator {
    override fun generateWallet(mnemonic: List<String>): Wallet {
        val solanaBip44 = SolanaBip44()
        val seed = MnemonicCode.toSeed(mnemonic, "")
        val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44CHANGE)
        val keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)

        return Wallet(
            publicKey = keyPair.getBase58PublicKey(),
            privateKey = keyPair.getBase58PrivateKey()
        )
    }
}