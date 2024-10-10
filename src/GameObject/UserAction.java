/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObject;
class Action {
    private String actionType;

    // Constructor
    public Action(String actionType) {
        this.actionType = actionType;
    }

    // Get the action type
    public String getActionType() {
        return actionType;
    }

    // Set the action type
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    // Perform the action
    public void perform() {
        System.out.println("Performing action: " + actionType);
    }
}
public class UserAction {
    private Action Click;
    private Action Space;
    private Action Enter;
    private int PresentScore;
    private int HighestScore;
    private String UserName;

    // Constructor
    public UserAction() {
        this.PresentScore = 0;
        this.HighestScore = 0;
        this.UserName = "";
    }

    // Constructor with parameters
    public UserAction(Action click, Action space, Action enter, int presentScore, int highestScore, String userName) {
        this.Click = click;
        this.Space = space;
        this.Enter = enter;
        this.PresentScore = presentScore;
        this.HighestScore = highestScore;
        this.UserName = userName;
    }

    // Getters and Setters for actions
    public Action getClick() {
        return Click;
    }

    public void setClick(Action click) {
        this.Click = click;
    }

    public Action getSpace() {
        return Space;
    }

    public void setSpace(Action space) {
        this.Space = space;
    }

    public Action getEnter() {
        return Enter;
    }

    public void setEnter(Action enter) {
        this.Enter = enter;
    }

    // Getters and Setters for scores and username
    public int getPresentScore() {
        return PresentScore;
    }

    public void setPresentScore(int presentScore) {
        this.PresentScore = presentScore;
    }

    public int getHighestScore() {
        return HighestScore;
    }

    public void setHighestScore(int highestScore) {
        this.HighestScore = highestScore;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    // Method to display the current score
    public void ShowScore() {
        System.out.println("Present Score: " + PresentScore);
        System.out.println("Highest Score: " + HighestScore);
    }
}
