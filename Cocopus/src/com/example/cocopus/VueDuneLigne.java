package com.example.cocopus;

import android.widget.TextView;

public class VueDuneLigne {
	TextView holderTitle;
	TextView holderDate;
	TextView holderAmount;
	
	public TextView getHolderTitle(){
		return holderTitle;
		
	}

	public void setHolderTitle(TextView holderTitle) {
		this.holderTitle = holderTitle;
	}

	public void setHolderDate(TextView holderDate) {
		this.holderDate = holderDate;
	}

	public TextView getHolderDate() {
		return holderDate;
	}

	public void setHolderAmount(TextView holderAmount) {
		this.holderAmount = holderAmount;
	}
	
	public TextView getHolderAmount() {
		return holderAmount;
	}
}
