package edu.iu.c322.assetservice.controller;

import edu.iu.c322.assetservice.client.LicensingClient;
import edu.iu.c322.assetservice.model.Asset;
import edu.iu.c322.assetservice.model.License;
import edu.iu.c322.assetservice.repository.AssetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private AssetRepository repository;
    private LicensingClient licensingClient;

    public AssetController(AssetRepository repository, LicensingClient licensingClient) {
        this.repository = repository;
        this.licensingClient = licensingClient;
    }


    @GetMapping
    public List<Asset> getAssets(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Asset getLicensing(@PathVariable int id){

        Optional<Asset> maybeAsset = repository.findById(id);
        if(maybeAsset.isPresent()){
            Asset asset = maybeAsset.get();
            Optional<License> maybeLicense = licensingClient
                    .getOrganization(asset.getLicenseId());
            if(maybeLicense.isPresent()){
                License license = maybeLicense.get();
                asset.setLicense(license);
                return asset;
            }
        } else {
            throw new IllegalStateException("licensing id is invalid.");
        }
        return null;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@RequestBody Asset asset){
        Asset addedAsset = repository.save(asset);
        return addedAsset.getId();
    }



}
