package com.tza.service;

import com.tza.entity.Release;
import com.tza.exception.ReleaseNotFoundException;
import com.tza.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public List<Release> listReleases() {
        return (List<Release>) releaseRepository.findAll();
    }

    @Override
    public Release findRelease(Integer id)
    {
        Optional<Release> optionalRelease = releaseRepository.findById(id);

        if(optionalRelease.isPresent())
            return optionalRelease.get();
        else
            throw new ReleaseNotFoundException("Ticket Not Found", id);
    }

}
