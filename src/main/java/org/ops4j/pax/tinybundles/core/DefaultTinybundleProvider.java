/*
 * Copyright 2011 Toni Menzel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.tinybundles.core;

import java.io.InputStream;
import javax.inject.Inject;
import javax.inject.Provider;
import org.ops4j.pax.tinybundles.core.intern.TinyBundleImpl;
import org.ops4j.store.Store;

/**
 * Provider which you can use with JSR330 compatible runtimes.
 *
 * @author Toni Menzel (tonit)
 * @since Jan 9, 2011
 */
public class DefaultTinybundleProvider implements Provider<TinyBundle> {

    final private Store<InputStream> m_store;
    final private BuildableBundle buildStrategy;

    @Inject
    public DefaultTinybundleProvider( Store<InputStream> store, BuildableBundle strategy )
    {
        m_store = store;
        buildStrategy = strategy;
    }

    /**
     * Start with a fresh bundle with this factory method.
     * You can then chain methodcalls thanks to the humane nature of {@link TinyBundle} interface.
     *
     * @return a new instance of a  {@link TinyBundle}. This is almost always the startingpoint of any interaction with {@link TinyBundle}.
     */
    public TinyBundle get()
    {
        return new TinyBundleImpl( buildStrategy, m_store );
    }
}
