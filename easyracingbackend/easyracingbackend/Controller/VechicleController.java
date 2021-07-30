package com.example.easyracing.easyracingbackend.Controller;

import com.example.easyracing.easyracingbackend.Model.Car;
import com.example.easyracing.easyracingbackend.Model.Competition;
import com.example.easyracing.easyracingbackend.Model.Event;
import com.example.easyracing.easyracingbackend.Model.Motorcycle;
import com.example.easyracing.easyracingbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/app/vechicle")
public class VechicleController {

    @Autowired
    MotorcycleRepository motorcycleRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CompetitionRepository competitionRepository;


    @CrossOrigin
    @GetMapping(value = "/car/all/{id}")
    public List<Car> getCars(@PathVariable(value = "id") int id){

        int id_driver = driverRepository.findByAccountId(id).getId();
        return carRepository.findByDriverId(id_driver);
    }

    @CrossOrigin
    @GetMapping(value = "/motorcycle/all/{id}")
    public List<Motorcycle> getMotorcycles(@PathVariable(value = "id") int id){

        int id_driver = driverRepository.findByAccountId(id).getId();
        return motorcycleRepository.findByDriverId(id_driver);
    }

    @CrossOrigin
    @PostMapping(value = "/motorcycle/add")
    public String persistMotorycle(@RequestParam("photo") MultipartFile photo,
                                   @RequestParam("brand") String brand,
                                   @RequestParam("model") String model,
                                   @RequestParam("year") String year,
                                   @RequestParam("capacity") String capacity,
                                   @RequestParam("horsepower") String horsepower,
                                   @RequestParam("homologation") String homologation,
                                   @RequestParam("id") String id) {

        Motorcycle moto = new Motorcycle();
        moto.setBrand(brand);
        moto.setModel(model);
        moto.setYear(Integer.parseInt(year));
        moto.setCapacity(Integer.parseInt(capacity));
        moto.setHomologation(homologation);
        moto.setHorsepower(Integer.parseInt(horsepower));


        try {
            InputStream inputStream =  photo.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            moto.photo = bytes;
        }catch (Exception e){
            System.out.println(e);

        }

        moto.setDriver(driverRepository.findByAccountId(Integer.parseInt(id)));
        motorcycleRepository.save(moto);

        return "Dodano motocykl";
    }

    @CrossOrigin
    @GetMapping(value = "/image/{id}")
    public byte[] getImage(@PathVariable int id) {
        byte[] image;
        try {
            image = motorcycleRepository.getOne(id).getPhoto();

        }catch(Exception e){

            image = carRepository.getOne(id).getPhoto();
        }



    return image;
    }

    @CrossOrigin
    @PostMapping(value = "/car/add")
    public String persistCar(@RequestParam("photo") MultipartFile photo,
                                    @RequestParam("brand") String brand,
                                    @RequestParam("model") String model,
                                    @RequestParam("year") String year,
                                    @RequestParam("capacity") String capacity,
                                    @RequestParam("horsepower") String horsepower,
                                    @RequestParam("homologation") String homologation,
                                    @RequestParam("id") String id) {

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(Integer.parseInt(year));
        car.setCapacity(Integer.parseInt(capacity));
        car.setHomologation(homologation);
        car.setHorsepower(Integer.parseInt(horsepower));


        try {
            InputStream inputStream =  photo.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            car.photo = bytes;
        }catch (Exception e){
            System.out.println(e);

        }

        car.setDriver(driverRepository.findByAccountId(Integer.parseInt(id)));
        carRepository.save(car);

        return "Dodano samochód";
    }


    @CrossOrigin
    @PostMapping(value = "/join")
    public void addVechicleToCompetition(@RequestParam("competition") int idCompetition,
                             @RequestParam("vechicle") int idVechicle,
                             @RequestParam("type") String type)throws Exception{

        Competition comp = competitionRepository.getOne(idCompetition);

        if(type.equals("car")){
            Car c = carRepository.getOne(idVechicle);
            comp.addCar(c);
            competitionRepository.save(comp);
        }else{
            Motorcycle m = motorcycleRepository.getOne(idVechicle);
            comp.addMotorcycle(m);
            competitionRepository.save(comp);
        }
    }

}
