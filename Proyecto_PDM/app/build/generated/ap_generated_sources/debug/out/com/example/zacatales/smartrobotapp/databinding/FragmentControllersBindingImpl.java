package com.example.zacatales.smartrobotapp.databinding;
import com.example.zacatales.smartrobotapp.R;
import com.example.zacatales.smartrobotapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentControllersBindingImpl extends FragmentControllersBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.action_to_previus_controllerFragment, 1);
        sViewsWithIds.put(R.id.action_to_bluetooth_ControllerFragment, 2);
        sViewsWithIds.put(R.id.action_to_route_ControllerFragment, 3);
        sViewsWithIds.put(R.id.textViewNameAppController, 4);
        sViewsWithIds.put(R.id.up_actionButton, 5);
        sViewsWithIds.put(R.id.left_actionButton, 6);
        sViewsWithIds.put(R.id.right_actionButton, 7);
        sViewsWithIds.put(R.id.back_actionButton, 8);
        sViewsWithIds.put(R.id.horn_actionButton, 9);
        sViewsWithIds.put(R.id.lights_actionButton, 10);
        sViewsWithIds.put(R.id.textViewVelocity, 11);
        sViewsWithIds.put(R.id.textViewInfoVelocity, 12);
        sViewsWithIds.put(R.id.seekBar, 13);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentControllersBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentControllersBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[2]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[1]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[3]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[8]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[9]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[6]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[10]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[7]
            , (android.widget.SeekBar) bindings[13]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[11]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[5]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.fragment == variableId) {
            setFragment((com.example.zacatales.smartrobotapp.ControllersFragment) variable);
        }
        else if (BR.isLightsActionButtonPressed == variableId) {
            setIsLightsActionButtonPressed((boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable com.example.zacatales.smartrobotapp.ControllersFragment Fragment) {
        this.mFragment = Fragment;
    }
    public void setIsLightsActionButtonPressed(boolean IsLightsActionButtonPressed) {
        this.mIsLightsActionButtonPressed = IsLightsActionButtonPressed;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): fragment
        flag 1 (0x2L): isLightsActionButtonPressed
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}