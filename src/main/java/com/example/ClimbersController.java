package com.example;

import com.example.lib.Climber;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

/**
 * Created by gschool on 2/18/17.
 */
@RestController
@RequestMapping("/climbers")
public class ClimbersController {

    @GetMapping(value={"/", ""})
    public Climber[] getClimbers() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
        Climber chris = new Climber("Chris", "Sharma", format.parse("03-23-95"), 35);
        Climber dave = new Climber("Dave", "Graham", format.parse("11-04-93"), 35);
        Climber tommy = new Climber("Tommy", "Caldwell", format.parse("04-04-85"), 38);

        return new Climber[]{chris, dave, tommy};
    }

    @DeleteMapping(value={"/{name}", "/{name}/"})
    public String deleteClimber(@PathVariable String name) {
        return "Deleting " + name;
    }

    @PatchMapping(value={"/{name}", "/{name}/"})
    public String patchClimber(@PathVariable String name) {
        return "Patching " + name;
    }

    @PostMapping({"/", ""})
    public String postClimber(@RequestBody Climber climber) {
        System.out.println(climber.toString());
        return climber.toString();
    }

}
