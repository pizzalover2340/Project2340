package gitmad.gatech.edu.project2340.Model;

public class AddDonationCommand extends AbstractCommand {
    Donation donation;

    public AddDonationCommand(Donation s) {
        donation = s;
    }

    @Override
    public boolean execute() {
        UserManagementFacade umf = UserManagementFacade.getInstance();
        umf.addDonation(donation);
        return true;
    }

    @Override
    public boolean undo() {
        UserManagementFacade umf = UserManagementFacade.getInstance();
        umf.removeDonation(donation);
        return true;
    }
}
