package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	
	//Initializing the Page Objects:
			public LoginPage()throws IOException{
				super();
				PageFactory.initElements(driver, this);
			}
			
			public String validateLoginPageTitle(){
				return driver.getTitle();
			}

}
