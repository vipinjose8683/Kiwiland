package kiwiland.trains.behavior;

import static org.testng.Assert.assertEquals;
import kiwiland.trains.KiwilandTrainApp;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class KiwilandStepdefs {
    
    private String inputString;
    
    private String[] result;

    @Given("^the input for graph of routes between towns is \"([^\"]*)\"$")
    public void the_input_for_graph_of_routes_between_towns_is(String arg1) throws Throwable {
        this.inputString = arg1;
    }

    @When("^the program is executed$")
    public void the_program_is_executed() throws Throwable {
        KiwilandTrainApp app = new KiwilandTrainApp();
        this.result = app.process(inputString);
    }

    @Then("^the Output No (\\d+) is \"([^\"]*)\"$")
    public void the_Output_No_is(int arg1, String arg2) throws Throwable {
        assertEquals(result[arg1 - 1], arg2, "Result at " + arg1);
        if (arg1 == 4) {
            Integer resultLength = this.result.length;
            for (int i = 0; i < resultLength; i++) {
                System.out.println("Output #" + (i + 1) + ": " + result[i]);
            }
        }
    }

}
