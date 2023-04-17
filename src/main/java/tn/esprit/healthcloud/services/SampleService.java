package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.repositories.SampleRepository;

@Service
public class SampleService implements ISampleService {
    @Autowired
    SampleRepository sampleRepository;
}
