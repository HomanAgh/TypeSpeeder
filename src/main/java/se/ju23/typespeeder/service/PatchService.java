package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Patch;
import se.ju23.typespeeder.entity.PatchRepository;

import java.util.List;

@Service
public class PatchService {
    private final PatchRepository patchRepository;

    @Autowired
    public PatchService(PatchRepository patchRepository) {
        this.patchRepository = patchRepository;
    }

    public List<Patch> getAllPatches(){
        return patchRepository.findAll();
    }

    public void displayNews() {
        List<Patch> patchList = getAllPatches();
        patchList.forEach(patch -> {
            System.out.println("ID: " + patch.getId());
            System.out.println("Version: " + patch.getVersion());
            System.out.println("Released: " + patch.getReleaseDate());
        });
    }
}
