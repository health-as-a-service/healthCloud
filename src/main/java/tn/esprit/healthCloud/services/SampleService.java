package tn.esprit.healthCloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.healthCloud.repositories.SampleRepository;

@Service
public class SampleService implements ISampleService {
    @Autowired
    SampleRepository sampleRepository;
}
