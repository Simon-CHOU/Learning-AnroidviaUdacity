package com.example.android.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int numberOfCoffees = 2;
    private int priceOfCoffees = 5;
    private int priceOfPapercup;
    private int priceOfWphipedCream = 1;
    private int priceOfChocolate = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrders(View view) {
        //Figure out who wants these coffees.
        EditText nameFiled = (EditText) findViewById(R.id.name_field);
        String customerName = nameFiled.getText().toString();

        // Figure out if the user wants whipped cream topping
        CheckBox checkBoxCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkBoxCream.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkBoxChocolate.isChecked();

        // displayPrice(numberOfCoffees * priceOfCoffees);
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String priceMessage = createOrderSummary(customerName, price, hasWhippedCream, hasChocolate);



        composeEmail(customerName, priceMessage);
        //displayMessage(priceMessage);
    }

    /**
     *
     * @param subject
     * @param text
     */
    public void composeEmail(String subject,String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * Calculates the price of the order.
     * <p>
     * numberOfCoffees is the number of cups of coffee ordered
     * priceOfCoffees  is the price of each cups of coffee
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int unitPrice = 0;
        if (addWhippedCream == true) {
            unitPrice = priceOfCoffees + priceOfWphipedCream;
        }
        if (addChocolate == true) {
            unitPrice = priceOfCoffees + priceOfChocolate;
        }
        return numberOfCoffees * unitPrice;
    }

    /**
     * Create summary of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants whipped cream topping
     * @param price           of the order
     * @return text summary
     */
    private String createOrderSummary(String customerName, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + customerName;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + numberOfCoffees;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (numberOfCoffees < 100)
            numberOfCoffees++;
        else
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();

        displayQuantity(numberOfCoffees);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (numberOfCoffees > 0)
            numberOfCoffees--;
        else
            Toast.makeText(this, "You cannot have less than 0 coffees", Toast.LENGTH_SHORT).show();
        displayQuantity(numberOfCoffees);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
//
//    /**
//     * This method displays the given price on the screen.
//     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//
//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView odrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        odrderSummaryTextView.setText(message);
//    }
}