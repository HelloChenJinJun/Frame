// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.chen.larkplayer.base;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class LarkRepositoryManager_Factory implements Factory<LarkRepositoryManager> {
  private final MembersInjector<LarkRepositoryManager> larkRepositoryManagerMembersInjector;

  private final Provider<Retrofit> retrofitProvider;

  private final Provider<LarkDatabase> databaseProvider;

  public LarkRepositoryManager_Factory(
      MembersInjector<LarkRepositoryManager> larkRepositoryManagerMembersInjector,
      Provider<Retrofit> retrofitProvider,
      Provider<LarkDatabase> databaseProvider) {
    assert larkRepositoryManagerMembersInjector != null;
    this.larkRepositoryManagerMembersInjector = larkRepositoryManagerMembersInjector;
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
    assert databaseProvider != null;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LarkRepositoryManager get() {
    return MembersInjectors.injectMembers(
        larkRepositoryManagerMembersInjector,
        new LarkRepositoryManager(retrofitProvider.get(), databaseProvider.get()));
  }

  public static Factory<LarkRepositoryManager> create(
      MembersInjector<LarkRepositoryManager> larkRepositoryManagerMembersInjector,
      Provider<Retrofit> retrofitProvider,
      Provider<LarkDatabase> databaseProvider) {
    return new LarkRepositoryManager_Factory(
        larkRepositoryManagerMembersInjector, retrofitProvider, databaseProvider);
  }
}
