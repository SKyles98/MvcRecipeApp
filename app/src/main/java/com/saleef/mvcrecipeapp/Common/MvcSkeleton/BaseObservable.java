package com.saleef.mvcrecipeapp.Common.MvcSkeleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservable<ListenerType> extends BaseViewMvc implements ObservableMvc<ListenerType> {

  private Set<ListenerType> mListenerTypes = new HashSet<>();
    @Override
    public void register(ListenerType listener) {
        if (!mListenerTypes.contains(listener))
        mListenerTypes.add(listener);
    }

    @Override
    public void unregister(ListenerType listener) {
        if (mListenerTypes.contains(listener)) {
            mListenerTypes.remove(listener);
        }
    }

    protected Set<ListenerType> getListeners(){
            return Collections.unmodifiableSet(mListenerTypes);
    }

    protected abstract void notifySubscribers(Set<ListenerType> listenerTypes);


}
