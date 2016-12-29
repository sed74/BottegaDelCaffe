package com.example.federicomarchesi.bottegadelcaffe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class CoffeTypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe_types);
        onEditPlayerName();
    }

    private void onEditPlayerName() {
//        final Player player = getCurrentPlayer();
        InputDialog inputDialog = new InputDialog(this, R.string.coffe_type_name, R.string.coffe_type_name);
//        inputDialog.setInitialInput(player.getName());
        inputDialog.setInputListener(new InputDialog.InputListener() {
            @Override
            public InputDialog.ValidationResult isInputValid(String newCoffeeType) {
                if (newCoffeeType.isEmpty()) {
                    return new InputDialog.ValidationResult(false, R.string.error_empty_name);
                }
//                Player playerWithSameName = gameManager.findPlayerByName(newCoffeeType);
//                if (playerWithSameName != null && playerWithSameName.getId() != getCurrentPlayer().getId()) {
//                    return new InputDialog.ValidationResult(false, R.string.error_duplicate_name);
//                }
                return new InputDialog.ValidationResult(true, 0);
            }


            @Override
            public void onConfirm(String newCoffeeType, String newCoffeeDescr) {
                DBHelper db = new DBHelper(CoffeTypesActivity.this);
                db.insertCoffeeType(newCoffeeType, newCoffeeDescr);
                Toast.makeText(CoffeTypesActivity.this, "records: " + db.numberOfRows(), Toast.LENGTH_SHORT).show();
            }
        });
        inputDialog.show();
    }

}
