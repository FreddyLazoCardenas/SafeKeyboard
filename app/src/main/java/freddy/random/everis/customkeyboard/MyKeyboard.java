package freddy.random.everis.customkeyboard;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private viewInterface mListener;
    private Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button0;

    private ImageButton buttonDelete, buttonEnter;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mListener = (viewInterface) context;
        LayoutInflater.from(context).inflate(R.layout.custom_keyboard, this, true);
        button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(this);

    }

    public void init(List<Integer> listNumber) {
        List<String> data = convertToStrings(listNumber);

        keyValues.put(R.id.button_1, String.valueOf(listNumber.get(1)));
        keyValues.put(R.id.button_2, String.valueOf(listNumber.get(2)));
        keyValues.put(R.id.button_3, String.valueOf(listNumber.get(3)));
        keyValues.put(R.id.button_4, String.valueOf(listNumber.get(4)));
        keyValues.put(R.id.button_5, String.valueOf(listNumber.get(5)));
        keyValues.put(R.id.button_6, String.valueOf(listNumber.get(6)));
        keyValues.put(R.id.button_7, String.valueOf(listNumber.get(7)));
        keyValues.put(R.id.button_8, String.valueOf(listNumber.get(8)));
        keyValues.put(R.id.button_9, String.valueOf(listNumber.get(9)));
        keyValues.put(R.id.button_0, String.valueOf(listNumber.get(0)));

        setTextValues(data);
    }

    private List<String> convertToStrings(List<Integer> listNumber) {
        /* Specify the size of the list up front to prevent resizing. */
        List<String> newList = new ArrayList<>(listNumber.size());
        for (Integer myInt : listNumber) {
            newList.add(String.valueOf(myInt));
        }
        return newList;
    }

    private void setTextValues(List<String> data) {
        button0.setText(data.get(0));
        button1.setText(data.get(1));
        button2.setText(data.get(2));
        button3.setText(data.get(3));
        button4.setText(data.get(4));
        button5.setText(data.get(5));
        button6.setText(data.get(6));
        button7.setText(data.get(7));
        button8.setText(data.get(8));
        button9.setText(data.get(9));
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        if (view.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else if (view.getId() == R.id.button_enter) {
            mListener.shuffle();
        } else {
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}
