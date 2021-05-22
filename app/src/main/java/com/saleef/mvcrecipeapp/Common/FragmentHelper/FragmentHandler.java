package com.saleef.mvcrecipeapp.Common.FragmentHelper;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.saleef.mvcrecipeapp.Common.FragmentHelper.FragmentFrameWrapper;

/**
 * Class we use to handle fragment transactions instead of instantiating a new in different views and fragments.
 * This makes our code more reusable
 */
public class FragmentHandler {

    private final Activity mActivity;
    private final FragmentManager mFragmentManager;
    private final FragmentFrameWrapper mFragmentFrameWrapper;

    public FragmentHandler(Activity activity,FragmentManager fragmentManager,FragmentFrameWrapper fragmentFrameWrapper){
        mActivity = activity;
        mFragmentManager = fragmentManager;
        mFragmentFrameWrapper = fragmentFrameWrapper;
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public void replaceFragment(Fragment fragment){
         replaceFragment(fragment,true,false);
    }

    private Fragment getCurrentFragment() {
        return mFragmentManager.findFragmentById(getFragmentFrameId());
    }

    private int getFragmentFrameId() {
         return mFragmentFrameWrapper.getFragmentFrame().getId();
    }

    private void replaceFragment(Fragment newFragment,boolean addtoBackStack,boolean clearBackStack) {
        if (clearBackStack) {
            if (mFragmentManager.isStateSaved()) {
                // If the state is saved we can't clear the back stack. Simply not doing this, but
                // still replacing fragment is a bad idea. Therefore we abort the entire operation.
                return;
            }
            mFragmentManager.popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        if (addtoBackStack){
            //Might cause issues later and will probably need to change null to the fragment
            ft.addToBackStack(null);
        }

        ft.replace(getFragmentFrameId(),newFragment,null);
        if (mFragmentManager.isStateSaved()) {
            // We acknowledge the possibility of losing this transaction if the app undergoes
            // save&restore flow after it is committed.
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }
    }
}
