package com.saleef.mvcrecipeapp.Common.MvcSkeleton;

import com.saleef.mvcrecipeapp.Common.MvcSkeleton.ViewMvc;

public interface ObservableMvc<ListenerType> extends ViewMvc {

    void register(ListenerType listener);
    void unregister(ListenerType listener);
}
