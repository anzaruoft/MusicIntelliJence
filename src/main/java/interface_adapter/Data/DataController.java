package interface_adapter.Data;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@RestController
@RequestMapping("/data")
public class DataController {

    private String dataFilePath = "http://ec2-3-139-82-243.us-east-2.compute.amazonaws.com/example_input.json";

    @PutMapping("/update")
    public ResponseEntity<String> updateData(@RequestBody String updatedData) {
        try {
            // Write the updated data to the file
            FileWriter fileWriter = new FileWriter(dataFilePath);
            fileWriter.write(updatedData);
            fileWriter.close();
            return ResponseEntity.status(200).body("Data updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to update data");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createData(@RequestBody String newData) {
        try {
            // Create or overwrite data file with new data
            FileWriter fileWriter = new FileWriter(dataFilePath);
            fileWriter.write(newData);
            fileWriter.close();
            return ResponseEntity.status(201).body("Data created successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to create data");
        }
    }
}
