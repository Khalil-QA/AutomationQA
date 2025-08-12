package com.tests.project_name.objects;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.tests.project_name.utils.CookieUtils;

import java.util.List;

public class Cookies {
	private io.restassured.http.Cookies cookies;

	public void injectCookiesToBrowser(WebDriver driver) {
		List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
		for (Cookie cookie : seleniumCookies) {
			driver.manage().addCookie(cookie);
		}
		driver.navigate().refresh();
	}
}
