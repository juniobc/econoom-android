package com.econoom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class NotasPager extends FragmentStatePagerAdapter {

	int mNumOfTabs;
	 
    public NotasPager(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
 
    @Override
    public Fragment getItem(int position) {
 
        switch (position) {
            case 0:
                ListaProduto tab1 = new ListaProduto();
                return tab1;
            case 1:
                ListaConta tab2 = new ListaConta();
                return tab2;
            case 2:
                ListaServico tab3 = new ListaServico();
                return tab3;
            default:
                return null;
        }
    }
 
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
