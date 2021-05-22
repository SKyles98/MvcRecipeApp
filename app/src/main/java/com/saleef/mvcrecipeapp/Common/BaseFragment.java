package com.saleef.mvcrecipeapp.Common;

import androidx.fragment.app.Fragment;

import com.saleef.mvcrecipeapp.Common.DependencyInjection.ControllerCompositionRoot;
import com.saleef.mvcrecipeapp.Common.MainActivity;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot mControllerCompositionRoot;

    //
    protected ControllerCompositionRoot getControllerCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((MainActivity) requireActivity()).getActivityCompositionRoot());

        }
        return mControllerCompositionRoot;
    }
}
