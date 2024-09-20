package com.accenture.client;

//import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

class KopeeteariaDashboardSeleniumTest {
	
	private ChromeDriver driver;
	private String baseUrl = "http://localhost:9090/kopeetearia-angular/";

	
	@BeforeClass
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/APPLICATIONS/chromedriver.exe");
    	driver = new ChromeDriver() ;
    	// Maximize window
        driver.manage().window().maximize();
        
        // Navigate to site URL
        driver.get(baseUrl);
        
        Thread.sleep(3000);
	}
    
	@Test
	public void addOrderTest() throws InterruptedException {
		System.out.println("Testing AddOrder");
        placeOrder("Latte", "4.5", true);
        Thread.sleep(1000);
        placeOrder("Cappuccino", "5.5", false);
        Thread.sleep(1000);
        Assert.assertTrue(driver.getPageSource().contains("Latte"));
	}
	
	@Test
	public void editOrderTest() throws InterruptedException{
		System.out.println("Testing editOrder");
		// edit feature
        Thread.sleep(3000);
        driver.findElementById("editOrd").click();
        // update feature
        driver.findElementById("updName").clear(); //clear text box
        driver.findElementById("updName").sendKeys("Today's Brew");
        driver.findElementById("updPrice").sendKeys("3.5");
        driver.findElementById("updDiscount").click();
        driver.findElementById("updOrd").click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getPageSource().contains("Today's Brew"));
	}
	
	@Test
	public void deleteOrderTest() throws InterruptedException {
		System.out.println("Testing deleteOrder");
		Thread.sleep(1000);
		driver.findElementById("deleteOrd").click();
		Assert.assertFalse(driver.getPageSource().contains("Today's Brew"));
	}

    
    public void placeOrder(String orderName, String orderPrice, boolean selectDiscount) {
    	driver.findElementById("ordName").sendKeys(orderName);
        driver.findElementById("ordPrice").sendKeys(orderPrice);
        // TODO: Remove [disabled] attribute in order.component.html to allow update for this field
        if (selectDiscount) {
        	driver.findElementById("ordDiscounted").click();
		}
        
        driver.findElementById("addOrderBtn").click();
    }
	
}
