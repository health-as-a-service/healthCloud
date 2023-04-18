package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Sample;
import tn.esprit.healthcloud.services.EmailSampleService;
import tn.esprit.healthcloud.services.ISampleService;
import javax.mail.MessagingException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Sample")
public class SampleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    ISampleService sampleService;
    @Autowired
    EmailSampleService emailSampleService;
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
    @PostMapping("/setSampleReady/{id}")
    public void setSampleReady(@PathVariable("id") int id) throws MessagingException {
        Sample sample = sampleService.getSample(id);
        LOGGER.info("{}", id);
        if (sample != null) {
            sample.setStatus("ready");
            sampleService.updateSample(sample, id);
            String userEmail = "heni.nechi@esprit.tn";
            emailSampleService.sendEmailSample(userEmail, "Sample ready", "Dear user,\\n\\nThe sample you submitted has been processed and is now ready for collection.");
        }
    }

}
