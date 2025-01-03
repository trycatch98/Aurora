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

package com.trycatch.crypto.di

import com.metaplex.lib.drivers.solana.SolanaConnectionDriver
import com.metaplex.lib.modules.token.TokenClient
import com.solana.Solana
import com.solana.networking.HttpNetworkingRouter
import com.solana.networking.Network
import com.solana.networking.RPCEndpoint
import com.trycatch.aurora.core.crypto.BuildConfig
import com.trycatch.crypto.RpcClient
import com.trycatch.crypto.SolanaRpcClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ClientModule {
    @Binds
    @Singleton
    abstract fun bindSolanaRpcClient(solanaRpcClient: SolanaRpcClient): RpcClient
}

@Module
@InstallIn(SingletonComponent::class)
internal object SolanaModule {
    @Provides
    @Singleton
    fun provideSolanaRPCEndpoint(): RPCEndpoint {
        val network = if (BuildConfig.DEBUG) {
            Network.testnet
        } else {
            Network.mainnetBeta
        }
        return RPCEndpoint.custom(
            URL(BuildConfig.SOLANA_RPC_URL),
            URL(BuildConfig.SOLANA_RPC_URL),
            network
        )
    }

    @Provides
    @Singleton
    fun provideSolana(endPoint: RPCEndpoint): Solana {
        val network = HttpNetworkingRouter(endPoint)
        return Solana(network)
    }

    @Provides
    @Singleton
    fun provideSolanaTokenClient(endPoint: RPCEndpoint): TokenClient {
        val solanaConnection = SolanaConnectionDriver(endPoint)
        return TokenClient(solanaConnection)
    }
}