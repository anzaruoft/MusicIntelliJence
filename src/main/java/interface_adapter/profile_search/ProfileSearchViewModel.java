package interface_adapter.profile_search;

import interface_adapter.ViewModel;

/**
 * The View Model for the Profile Search Use Case.
 */

public class ProfileSearchViewModel extends ViewModel<ProfileSearchState> {

    public ProfileSearchViewModel() {
        super("profile search");
        setState(new ProfileSearchState());
    }

}
