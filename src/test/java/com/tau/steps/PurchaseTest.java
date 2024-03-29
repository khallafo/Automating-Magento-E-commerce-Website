package com.tau.steps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.tau.Pages.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class PurchaseTest extends TestBase{
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;
    WishlistPage wishlistPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderSuccessPage orderSuccessPage;

    @Test
    public void VerifyPurchase_Products()
    {
        homePage=new HomePage(driver);
        homePage.Open_login_CreateAccPage();
        loginPage=new LoginPage(driver);
        loginPage.LogIn("mahmoudkhallaf288@gmail.com","123456");
        accountPage=new AccountPage(driver);
        accountPage.Open_WishListPage();
        wishlistPage=new WishlistPage(driver);

        cartPage=new CartPage(driver);
        cartPage.ProceedToCheckout();
        checkoutPage=new CheckoutPage(driver);

        checkoutPage.SetCheckout("ABC","New York","542896","123456","Florida","United States");
        Assert.assertEquals(checkoutPage.shipCost.getText(),"$35.00");
        checkoutPage.setCheckout2();
        Assert.assertEquals(checkoutPage.totalCost.getText(),"$620.00");
        checkoutPage.SetOrder();
        orderSuccessPage=new OrderSuccessPage(driver);
        Assert.assertTrue(orderSuccessPage.msg.getText().contains("Your order # is:"));
        System.out.println(" Your order number for your record = " + orderSuccessPage.orderNum.getText());
    }
}
