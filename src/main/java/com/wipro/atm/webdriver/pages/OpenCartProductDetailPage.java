package com.wipro.atm.webdriver.pages;

import com.wipro.atm.webdriver.model.Product;
import com.wipro.atm.webdriver.utils.BaseUtility;
import com.wipro.atm.webdriver.utils.Constants;
import com.wipro.atm.webdriver.utils.WaitForElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.wipro.atm.webdriver.utils.BaseUtility.getAttributeValue;
import static com.wipro.atm.webdriver.utils.BaseUtility.getElementText;

public class OpenCartProductDetailPage extends AbstractPage {

    public static String Prodprice;

    @FindBy(xpath = "//a[text()='Add to Wish List']")
    private WebElement lnkAddToWishList;

    @FindBy(xpath = "//img[@class='close']")
    private WebElement imgClose;

    @FindBy(xpath = "//a[contains(text(),'Wish List')]")
    private WebElement tabWishList;

    @FindBy(xpath = "//div[@class='price']")
    private WebElement lblPrice;

    @FindBy(xpath = "//a[@title='Pound Sterling']")
    private WebElement lnkPoundSterling;

    @FindBy(xpath = "//img[@title='Add to Cart']")
    private WebElement imgAddProductToCart;

    @FindBy(xpath = "//img[@title='Remove']")
    private WebElement imgProductRemove;

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//h1[text()='My Account']")
    private WebElement lblYourAccount;

    @FindBy(xpath = "//input[@id='button-cart']")
    private WebElement btnAddToCart;

    @FindBy(xpath = "//a[text()='Shopping Cart']")
    private WebElement lnkShoppingCart;

    @FindBy(xpath = "//input[contains(@name,'quantity')]")
    private WebElement txtProductQuantity;

    @FindBy(xpath = "//input[@title='Update']")
    private WebElement btnUpdateCart;

    public OpenCartProductDetailPage(WebDriver driver) {

        super(driver);
    }

    /**
     * below method will add the selected product to the wish list
     */

    public OpenCartProductDetailPage addProductToWistList() {
        WaitForElements.waitForElementClickable(driver, 10, lnkAddToWishList);
        lnkAddToWishList.click();
        imgClose.click();
        tabWishList.click();
        return new OpenCartProductDetailPage(driver);
    }

    /**
     * below method will add the product to the cart
     */

    public OpenCartProductDetailPage addProductToTheCart() {
        WaitForElements.waitForElementClickable(driver, 10, lnkPoundSterling);
        lnkPoundSterling.click();
        Prodprice = getElementText(lblPrice);
        Assert.assertEquals(Prodprice, Constants.PRODUCT_PRICE);
        imgAddProductToCart.click();
        WaitForElements.waitForElementClickable(driver, 10, imgClose);
        imgClose.click();
        return new OpenCartProductDetailPage(driver);
    }

    /**
     * below method will remove the product from the wish list
     */

    public OpenCartProductDetailPage removeProductFromWishList() {
        WaitForElements.waitForElementClickable(driver, 10, imgProductRemove);
        imgProductRemove.click();
        btnContinue.click();
        Assert.assertEquals(getElementText(lblYourAccount), Constants.YOUR_ACCOUNT);
        WaitForElements.waitForElementClickable(driver, 10, lblYourAccount);
        return new OpenCartProductDetailPage(driver);
    }

    /**
     * below method will add selected product to the cart,update the QTY in cart and verify the same
     * @param product - product Qty
     * @return
     */
    public OpenCartProductDetailPage addProductUpdateQtyAndVerifyQtyInCart(Product product){
        WaitForElements.waitForElementClickable(driver, 10, btnAddToCart);
        btnAddToCart.click();
        WaitForElements.waitForElementToBeVisible(driver,20,lnkShoppingCart);
        lnkShoppingCart.click();
        WaitForElements.waitForElementClickable(driver, 10, txtProductQuantity);
        txtProductQuantity.clear();
        WaitForElements.waitForElementClickable(driver,20,txtProductQuantity);
        txtProductQuantity.sendKeys(product.getProductQty());
        WaitForElements.waitForElementClickable(driver,20,txtProductQuantity);
        btnUpdateCart.click();
        Assert.assertEquals(getAttributeValue(txtProductQuantity,"value"), product.getProductQty());
        lnkShoppingCart.click();
        return new OpenCartProductDetailPage(driver);
    }

    /**
     * below method to check whether element is displayed or not
     * @return - true or false
     */
    public boolean isElementDisplayed() {

        return BaseUtility.isElementDisplayed(imgClose);
    }


}
