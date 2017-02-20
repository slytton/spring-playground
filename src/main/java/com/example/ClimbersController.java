package com.example;

import com.example.lib.Climber;
import com.example.lib.Competition;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by gschool on 2/18/17.
 */
@RestController
@RequestMapping("/climbers")
public class ClimbersController {

    private Map<Integer, Climber> climberMap;



    @GetMapping(value={"/", ""})
    private Climber[] getClimbers() throws Exception{
        return climberMap.values().toArray(new Climber[]{});
    }

    @GetMapping({"/{climberId}", "/{climberId}/"})
    public Climber getClimber(@PathVariable String climberId) {
        Integer id = Integer.parseInt(climberId);
        return getClimberMap().get(id);
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
        return climber.toString();
    }

    private Competition[] generateCompetitions(Integer numComps) {
        Competition[] compArray= new Competition[numComps];
        Integer compNum = 0;
        for(int i = 0; i < numComps; i++) {
            compNum = i + 1;
            compArray[i] = new Competition(compNum, "Comp " + compNum, new Random().nextInt(50) + 1);
        }

        return compArray;
    }

    private Map<Integer, Climber> generateClimbers() throws Exception {
        Map<Integer, Climber> climberMap = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
        Climber chris = new Climber(1, "Chris", "Sharma", format.parse("03-23-95"), 35);
        chris.setCompetitions(generateCompetitions(10));
        climberMap.put(1, chris);
        Climber dave = new Climber(2, "Dave", "Graham", format.parse("11-04-93"), 35);
        dave.setCompetitions(generateCompetitions(7));
        climberMap.put(2, dave);
        Climber tommy = new Climber(3, "Tommy", "Caldwell", format.parse("04-04-85"), 38);
        tommy.setCompetitions(generateCompetitions(3));
        climberMap.put(3, tommy);

        return climberMap;
    }

    public Map<Integer, Climber> getClimberMap() {
        try {
            if (climberMap == null || climberMap.size() == 0) setClimberMap(generateClimbers());
        } catch (Exception e) {
            System.out.println(e);
        }
        return climberMap;
    }

    public void setClimberMap(Map<Integer, Climber> climberMap) {
        this.climberMap = climberMap;
    }
}
