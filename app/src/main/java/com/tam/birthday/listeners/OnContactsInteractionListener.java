package com.tam.birthday.listeners;

/**
 * This interface must be implemented by any activity that loads the fragment ContactsListFragment. When an
 * interaction occurs, such as touching an item from the ListView, these callbacks will
 * be invoked to communicate the event back to the activity.
 *
 * Created by Manoj on 2/9/2016.
 */
public interface OnContactsInteractionListener {

    /**
     * Called when a contact is selected from the ListView.
     * TODO: need to pass a unique ID by which we can recognize the a contact record.
     */
    public void onContactSelected();

    /**
     * Called when the ListView selection is cleared like when
     * a contact search is taking place or is finishing.
     * TODO: need to find a place from where I can invoke this.
     * TODO: this will probably we used once I implement the loader to get data from facebook and
     * TODO: searchable interface
     */
    public void onSelectionCleared();

}
