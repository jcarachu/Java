package com.tza.service;

import com.tza.entity.Release;

import java.util.List;

public interface ReleaseService {
    List<Release> listReleases();
    Release findRelease(Integer id);
}


