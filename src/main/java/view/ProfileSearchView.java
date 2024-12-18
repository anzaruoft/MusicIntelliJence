package view;

import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;
import java.util.List;

import interface_adapter.profile_search.ProfileSearchController;
import interface_adapter.profile_search.ProfileSearchPresenter;
import interface_adapter.profile_search.ProfileSearchState;
import interface_adapter.profile_search.ProfileSearchViewModel;

// Potentially Dont need
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;


/**
 * The View for when the user is searching for other users.
 */
public class ProfileSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "profile search";
    private final ProfileSearchViewModel profileSearchViewModel;

    // Defining Search Text box, Search and Cancel Buttons, Search Error Field
    private final JTextField usernameSearchField = new JTextField(30);
    private final JLabel searchErrorField = new JLabel();

    private final JButton searchButton;
    private final JButton cancelButton;

    private ProfileSearchController profileSearchController;
    private ProfileSearchPresenter profileSearchPresenter;
    private FeedViewModel feedViewModel;

    public ProfileSearchView(ProfileSearchViewModel profileSearchViewModel) {

        this.profileSearchViewModel = profileSearchViewModel;
        this.profileSearchViewModel.addPropertyChangeListener(this);

        // Title of the page
        final JLabel title = new JLabel("Profile Search");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel profileSearchBarInfo = new LabelTextPanel(
                new JLabel("Search Profiles"), usernameSearchField);

        final JPanel buttons = new JPanel();
        searchButton = new JButton("Search");
        buttons.add(searchButton);
        cancelButton = new JButton("Cancel");
        buttons.add(cancelButton);

        cancelButton.setBackground(Color.RED);
        searchButton.setBackground(Color.GREEN);

        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            final ProfileSearchState currentState = profileSearchViewModel.getState();
                            profileSearchController.execute(
                                    currentState.getSearchedUsername(), currentState.getThisUsername()
                            );
                        }
                    }
                });

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (profileSearchPresenter != null) {
                            profileSearchPresenter.switchToFeedView();
                        }
                        else {
                            System.out.println("ProfileSearchPresenter is null");
                        }
                    }
                }
        );

        usernameSearchField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ProfileSearchState currentState = profileSearchViewModel.getState();
                currentState.setSearchedUsername(usernameSearchField.getText());
                profileSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(profileSearchBarInfo);
        this.add(buttons);
        this.add(searchErrorField);

        this.setBackground(Color.PINK);
    }

    /**
     * React to a button click that results in Event.
     * @param evt the ActionEvent to react to.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ProfileSearchState state = (ProfileSearchState) evt.getNewValue();
        setFields(state);
        searchErrorField.setText(state.getErrorMessage());
    }

    private void setFields(ProfileSearchState state) {
        usernameSearchField.setText(state.getSearchedUsername());
    }

    public String getViewName() {
        return viewName;
    }

    public void setProfileSearchController(ProfileSearchController profileSearchController) {
        this.profileSearchController = profileSearchController;
    }

    public void setProfileSearchPresenter(ProfileSearchPresenter profileSearchPresenter) {
        this.profileSearchPresenter = profileSearchPresenter;
    }
}
