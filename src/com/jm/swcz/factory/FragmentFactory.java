package com.jm.swcz.factory;

import android.app.Fragment;

import com.jm.swcz.R;
import com.jm.swcz.ui.fragment.DecisionFragment;
import com.jm.swcz.ui.fragment.DismountingFragment;
import com.jm.swcz.ui.fragment.IndexFragment;
import com.jm.swcz.ui.fragment.MaterialFragment;
import com.jm.swcz.ui.fragment.MoreFragment;

public class FragmentFactory {

	public static Fragment getInstanceByIndex(int index){
		Fragment fragment = null;
		switch(index){
		case R.id.rb_index:
            fragment = new IndexFragment();
            break;
		case R.id.rb_dismounting:
            fragment = new DismountingFragment();
            break;
		case R.id.rb_material:
            fragment = new MaterialFragment();
            break;
        case R.id.rb_decision:
            fragment = new DecisionFragment();
            break;
        case R.id.rb_more:
            fragment = new MoreFragment();
            break;
		}
		return fragment;
	}
}
