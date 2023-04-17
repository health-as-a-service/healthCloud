package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Pharmacie;
import tn.esprit.healthcloud.entities.Sample;
import tn.esprit.healthcloud.services.IPharmacieService;
import tn.esprit.healthcloud.services.ISampleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Sample")
public class SampleController {
    @Autowired
    ISampleService sampleService;
    @PostMapping("/add-sample")
    public Sample addSample(@RequestBody Sample s) {
        Sample sample = sampleService.addSample(s);
        return sample;
    }


    @DeleteMapping("/delete-sample/{id-sample}")
    public void deleteSample(@PathVariable("id-sample") int id)
    {
        sampleService.deleteSample(id);
    }

    @GetMapping("/getSample/{id}")
    public Sample getSampleById(@PathVariable int id)
    {
        return sampleService.getSample(id);
    }
    @GetMapping("/getAllSample")
    public List<Sample> getAllSample()
    {
        return sampleService.getAllSample();
    }
    @PutMapping("/updateSample/{idSample}")
    public Sample updateSample(@PathVariable("idSample") int id_sample, @RequestBody Sample sample)
    {
        return sampleService.updateSample(sample,id_sample);
    }
}
