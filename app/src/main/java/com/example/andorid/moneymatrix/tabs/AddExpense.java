package com.example.andorid.moneymatrix.tabs;

import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andorid.moneymatrix.R;
import com.example.andorid.moneymatrix.database.CategoriesExpenseLab;
import com.example.andorid.moneymatrix.models.CategoriesExpense;

import java.util.ArrayList;
import java.util.List;

//This class is the Add expense tab of the mainActivity
//Here the tasks such as creation of the expense with selection of categories is carried out.


public class AddExpense extends Fragment {


    private CategoriesExpense mCategoriesExpense;
    //An array to store the categories which are available to the user.
    private List<CategoriesExpense> mCategoriesBank = new ArrayList<>();
    private ImageView mSelectedCategoryImage;
    private TextView mSelectedCategoryName;
    private RecyclerView mRecyclerView;
    private EditText mExpenseEditText;


    //Overiden method of appCompatActivity to create the activity;
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadCategories();//call of method to populate the activity in the arrayList of categories.
    }


    //Overiden method of fragment to create the view of fragment in the activity.
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_expense_tab, container, false);

        //Initialising the recyclerView of CategoriesExpense.
        mRecyclerView = (RecyclerView) v.findViewById(R.id.categories_recyclerView);

        //Setting the GridLayout of the recyclerView with total numbers of rows (5).
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));

        //Setting the adapter of recyclerView.
        //Putting the arrayList in the adapter mCategoriesBank.
        mRecyclerView.setAdapter(new CategoryAdapter(mCategoriesBank));

        //ImageView for the image of category selected by the user.
        mSelectedCategoryImage = (ImageView) v
                .findViewById(R.id.selected_category_imageView);

        //Setting onClickListener of the selected category ImageView.
        mSelectedCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mSelectedCategoryImage.setImageDrawable(null);
                mSelectedCategoryName.setText(null);
            }
        });

        //TextView for the name of category selected by the user.
        mSelectedCategoryName = (TextView) v
                .findViewById(R.id.selected_category_textView);

        //Initially setting the TextView value to null so that the garbage value is not displayed.
        mSelectedCategoryName.setText(null);


        //EditText for adding the expense Value.
        mExpenseEditText = (EditText) v.findViewById(R.id.enter_expense_editText);

        //Button used to add the Expense and category selected by the user into the database.
        Button addExpenseButton = (Button) v.findViewById(R.id.add_expense_button);

        //Setting onClickListener on the AddExpenseButton.
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                //Getting the value of the EditText
                String expense = mExpenseEditText.getText().toString();

                //Setting the expense using setter of model CategoriesExpense.
                mCategoriesExpense.setExpense(expense);

                try {
                    createExpense();//Method used to save the database.
                    Snackbar.make(getView(),
                            "EXPENSE ADDED", Snackbar.LENGTH_SHORT).show();
                } catch (SQLException e) {//Caught if the database is not saved.
                    Snackbar.make(getView(),
                            "DATABASE NOT SAVED", Snackbar.LENGTH_SHORT).show();

                }

            }
        });
        return v;
    }


    //Class for declaring the viewHolder for the recyclerView of categories.
    private class CategoryHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private ImageView mImageView;//ImageView that holds the category image in recyclerView.
        private TextView mTextView;//TextView that holds the category name in recyclerView.


        //Method that is used in the recyclerView adapter class in onCreateViewHolder method.
        public CategoryHolder (LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.category_recycler_view_view_holder, container, false));

            mImageView = (ImageView) itemView.findViewById(R.id.categories_icon_imageButton);
            mImageView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.categories_name_textView);

        }

        //Method used by the adapter to hold the data to the ViewHolder in onBindViewHolder.
        public void bindCategory (CategoriesExpense categoriesExpense) {
            mCategoriesExpense = categoriesExpense;
            mImageView.setImageResource(categoriesExpense.getCategoryImageId());
            mTextView.setText(categoriesExpense.getCategoryNameId());
        }

        //Method used for setting the onClickListener on the ViewHolder.
        @Override
        public void onClick (View v) {

            //Getting the adapterPosition of the Holder clicked.
            CategoryHolder holder = (CategoryHolder) mRecyclerView
                    .findViewHolderForAdapterPosition(getAdapterPosition());

            //Getting the clicker ViewHolder image and text.
            Drawable drawable = holder.mImageView.getDrawable();
            String text = holder.mTextView.getText().toString();

            //Setting the image and name of selected category from the selected viewHolder.
            mSelectedCategoryImage.setImageDrawable(drawable);
            mSelectedCategoryName.setText(text);
            mCategoriesExpense.setCategoryName(text);
        }

    }

    //Class to declare the adapter of the recyclerView.
    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
        List<CategoriesExpense> mCategories;//List to store the categories that the adapter
        //will pass to ViewHolder.

        //Method called in the onCreateView to pass the array that needs to be Showed in adapter.
        //Method to call the adapter.
        public CategoryAdapter (List<CategoriesExpense> categories) {
            mCategories = categories;
        }


        //This method will create a ViewHolder whenever needed.
        @Override
        public CategoryHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new CategoryHolder(inflater, parent);
        }

        //This method will bind the data to the viewHolder.
        @Override
        public void onBindViewHolder (CategoryHolder holder, int position) {
            CategoriesExpense categoriesExpense = mCategories.get(position);
            holder.bindCategory(categoriesExpense);
        }

        //This method will return the number of items in the arrayList.
        @Override
        public int getItemCount () {
            return mCategories.size();
        }


    }

    //Method used to populate the arrayList with the categories when the activity is created.
    private void loadCategories () {
        mCategoriesBank.add(new CategoriesExpense(R.string.aircraft, R.drawable.aircraft));
        mCategoriesBank.add(new CategoriesExpense(R.string.apartment, R.drawable.apartment));
        mCategoriesBank.add(new CategoriesExpense(R.string.coffee, R.drawable.chokolate));
        mCategoriesBank.add(new CategoriesExpense(R.string.car, R.drawable.car));
        mCategoriesBank.add(new CategoriesExpense(R.string.clothes, R.drawable.clothes));
    }

    //Method that stores the values in the database of the categories selected.
    private void createExpense () {
        mCategoriesExpense.getExpense();
        mCategoriesExpense.getCategoryName();
        CategoriesExpenseLab.get(getActivity()).addExpense(mCategoriesExpense);
    }
}
