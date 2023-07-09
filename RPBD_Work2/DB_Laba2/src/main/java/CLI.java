import DB_hotel.*;
import Mappers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

public class CLI {
    private RoomMapper roomMapper;
    private PassportMapper passportMapper;
    private RegistrationMapper registrationMapper;
    private ServiceMapper serviceMapper;
    private StaffMapper staffMapper;
    private VisitorMapper visitorMapper;
    private VisitorServiceMapper visitorServiceMapper;
    private WorkinghoursMapper workinghoursMapper;

    public CLI() throws IOException {
        createCLI();
    }

    public static String write() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        return reader.readLine();
    }

    private void createCLI() throws IOException {
        roomMapper = new RoomMapper();
        passportMapper = new PassportMapper();
        registrationMapper = new RegistrationMapper();
        serviceMapper = new ServiceMapper();
        staffMapper = new StaffMapper();
        visitorMapper = new VisitorMapper();
        visitorServiceMapper = new VisitorServiceMapper();
        workinghoursMapper = new WorkinghoursMapper();
        boolean isWorking = true;
        int answer = 0;
        int insideAnswer = 0;

        while (isWorking) {
            chooseMainMenu();
            try {
                answer = Integer.parseInt(write());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (answer) {
                case 0:{
                    isWorking = false;
                break;
                }
                case 1: { // PassportEntity
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsidePassport();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var passportEntity = passportMapper.findAll();
                                for (int i = 0; i < passportEntity.size(); i++) {
                                    System.out.println((i + 1) + ". " + passportEntity.get(i));
                                }
                                break;
                            } //Write passportEntity

                            case 2: {
                                PassportEntity passportEntity = new PassportEntity();
                                System.out.println("Write address: ");
                                passportEntity.setAddress(write());
                                System.out.println("Write number: ");
                                passportEntity.setNumber(Integer.valueOf(write()));
                                System.out.println("Write date extradition (yyyy-mm-dd): ");
                                passportEntity.setDateExtradition(Date.valueOf(write()));
                                System.out.println("Write passport issuance: ");
                                passportEntity.setPassportIssuance(write());
                                passportMapper.save(passportEntity);

                                break;
                            } //Add a passport entity

                            case 3: {
                                var isEdit = true;
                                var passportEntity = passportMapper.findAll();
                                for (int i = 0; i < passportEntity.size(); i++) {
                                    System.out.println((i + 1) + ". " + passportEntity.get(i));
                                }
                                System.out.print("What passport you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                PassportEntity passportEntityEdit = passportEntity.get(id - 1);
                                while (isEdit) {
                                    passportEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());
                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write new address ");
                                            passportEntityEdit.setAddress(write());
                                            break;
                                        } // edit address
                                        case 2: {
                                            System.out.println("Write new number ");
                                            passportEntityEdit.setNumber(Integer.valueOf(write()));
                                            break;
                                        } // edit number

                                        case 3: {
                                            System.out.println("Write new date extradition: ");
                                            passportEntityEdit.setDateExtradition(Date.valueOf(write()));
                                            break;
                                        } // edit date extradition

                                        case 4: {
                                            System.out.println("Write new passport issuance: ");
                                            passportEntityEdit.setPassportIssuance(write());
                                            break;
                                        } // edit passport issuance:

                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }

                                }

                                passportMapper.edit(passportEntityEdit);
                                break;
                            } // Edit a passport entity

                            case 4: {
                                var passportEntity = passportMapper.findAll();
                                for (int i = 0; i < passportEntity.size(); i++) {
                                    System.out.println((i + 1) + ". " + passportEntity.get(i));
                                }
                                System.out.print("What passport you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                passportMapper.delete(passportEntity.get(id - 1));
                                break;
                            } //Delete passport entity

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    passportFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());
                                    /*
                                    *  System.out.println("1. Address");
                                        System.out.println("2. Number");
                                        System.out.println("3. Date extradition");
                                        System.out.println("4. Passport issuance");
                                    * */
                                    switch (infoKey) {
                                        case 1: {
                                            System.out.println("Write: ");
                                            var passportEntityFind = passportMapper.findAllByAddress(write());
                                            for (PassportEntity passportEntity : passportEntityFind) {
                                                System.out.println(passportEntity);
                                            }
                                            break;
                                        } // find address

                                        case 2: {
                                            System.out.println("Write: ");
                                            var passportEntityFind = passportMapper.findAllByNumber(write());
                                            for (PassportEntity passportEntity : passportEntityFind) {
                                                System.out.println(passportEntity);
                                            }

                                            break;
                                        }//find number

                                        case 3: {
                                            System.out.println("Write: ");
                                            var passportEntityFind = passportMapper.findAllByDateExtradition(write());
                                            for (PassportEntity passportEntity : passportEntityFind) {
                                                System.out.println(passportEntity);
                                            }

                                            break;
                                        }//find Order number

                                        case 4: {
                                            System.out.println("Write: ");
                                            var passportEntityFind = passportMapper.findAllByPassportIssuance(write());
                                            for (PassportEntity passportEntity : passportEntityFind) {
                                                System.out.println(passportEntity);
                                            }


                                            break;
                                        }//find Date of order
                                        default: {
                                            isFind = false;
                                            break;
                                        }
                                    }
                                }
                                break;

                            } //  Find field in passport
                            default: {
                                insideMenu = false;
                                break;
                            }
                        }
                    }
                }
                case 2: { // Staff
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsideStaff();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var staff = staffMapper.findAll();
                                for (int i = 0; i < staff.size(); i++) {
                                    System.out.println((i + 1) + ". " + staff.get(i));
                                }
                                break;
                            } //Write staff

                            case 2: {
                                StaffEntity staff = new StaffEntity();
                                System.out.println("Write name of staff: ");
                                staff.setName(write());
                                System.out.println("Write surname of staff: ");
                                staff.setSurname(write());
                                System.out.println("Write patronymic of staff: ");
                                staff.setPatronymic(write());
                                System.out.println("Write passport id of staff: ");

                                var passport = passportMapper.findAll();
                                for (int i = 0; i < passport.size(); i++) {
                                    System.out.println((i + 1) + ". " + passport.get(i).getPassportIssuance());
                                }
                                System.out.print("Write passport issuance: ");
                                var passports = passport.get(Integer.parseInt(write()) - 1);
                                staff.setPassportByPassportId(passports);

                                staffMapper.save(staff);
                                break;
                            } //Add a staff

                            case 3: {
                                var isEdit = true;
                                var staff = staffMapper.findAll();
                                for (int i = 0; i < staff.size(); i++) {
                                    System.out.println((i + 1) + ". " + staff.get(i));
                                }
                                System.out.print("What staff you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                StaffEntity staffEdit = staff.get(id - 1);
                                while (isEdit) {
                                    staffEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());

                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write a new surname: ");
                                            staffEdit.setSurname(write());
                                            break;
                                        } //Surname

                                        case 2: {
                                            System.out.println("Write a new name: ");
                                            staffEdit.setName(write());
                                            break;
                                        } //Name

                                        case 3: {
                                            System.out.println("Write a new patronymic: ");
                                            staffEdit.setPatronymic(write());
                                            break;
                                        } // Patronymic

                                        case 4: {
                                            List<PassportEntity> passports = passportMapper.findAll();
                                            for (int i = 0; i < passports.size(); i++) {
                                                System.out.println((i + 1) + ". " + passports.get(i));
                                            }
                                            System.out.print("write a new passport id ");
                                            var passport = passports.get(Integer.valueOf(write()) - 1);
                                            staffEdit.setPassportByPassportId(passport);
                                            break;
                                        } // passport id

                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }

                                }
                                staffMapper.edit(staffEdit);
                                break;
                            }  //Edit a staff

                            case 4: {
                                var staff = staffMapper.findAll();
                                for (int i = 0; i < staff.size(); i++) {
                                    System.out.println((i + 1) + ". " + staff.get(i));
                                }
                                System.out.print("What staff you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                staffMapper.delete(staff.get(id - 1));
                                break;
                            } //Delete staff

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    staffFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());
                                    switch (infoKey) {
                                        case 1: {
                                            System.out.println("Write: ");
                                            var staffFind = staffMapper.findAllBySurname(write());
                                            for (StaffEntity staff : staffFind) {
                                                System.out.println(staff);
                                            }
                                            break;
                                        } // Find Surname

                                        case 2: {
                                            System.out.println("Write: ");
                                            var staffFind = staffMapper.findAllByName(write());
                                            for (StaffEntity staff : staffFind) {
                                                System.out.println(staff);
                                            }
                                            break;
                                        } // Find Name

                                        case 3: {
                                            System.out.println("Write: ");
                                            var staffFind = staffMapper.findAllByPatronymic(write());
                                            for (StaffEntity staff : staffFind) {
                                                System.out.println(staff);
                                            }
                                            break;
                                        } // Find patronymic

                                        default: {
                                            isFind = false;
                                            break;
                                        }

                                    }
                                }


                                break;
                            } //  Find field in staff

                            default: {
                                insideMenu = false;
                                break;
                            }
                        }

                    }
                    break;
                }

                case 3: { // Visitor
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsideVisitor();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var visitor = visitorMapper.findAll();
                                for (int i = 0; i < visitor.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitor.get(i));
                                }
                                break;
                            } //Write visitor

                            case 2: {
                                VisitorEntity visitor = new VisitorEntity();
                                System.out.println("Write name of visitor: ");
                                visitor.setName(write());
                                System.out.println("Write surname of visitor: ");
                                visitor.setSurname(write());
                                System.out.println("Write patronymic of visitor: ");
                                visitor.setPatronymic(write());
                                System.out.println("Write birthday of visitor (yyyy-mm-dd): ");
                                visitor.setBirthday(Date.valueOf(write()));
                                System.out.println("Write gender of visitor  ");
                                visitor.setGender(Integer.valueOf(write()));

                                var passport = passportMapper.findAll();
                                for (int i = 0; i < passport.size(); i++) {
                                    System.out.println((i + 1) + ". " + passport.get(i).getPassportIssuance());//ДОПУСТИМ!
                                }
                                System.out.print("Write passport issuance: ");
                                var passports = passport.get(Integer.parseInt(write()) - 1);
                                visitor.setPassportByPassportId(passports);

                                visitorMapper.save(visitor);
                                break;
                            } //Add a visitor

                            case 3: {
                                var isEdit = true;
                                var visitor = visitorMapper.findAll();
                                for (int i = 0; i < visitor.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitor.get(i));
                                }
                                System.out.print("What visitor you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                VisitorEntity visitorEdit = visitor.get(id - 1);
                                while (isEdit) {
                                    visitorEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());

                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write a new name: ");
                                            visitorEdit.setName(write());
                                            break;
                                        } //Name

                                        case 2: {
                                            System.out.println("Write a new surname: ");
                                            visitorEdit.setSurname(write());
                                            break;
                                        } //surname

                                        case 3: {
                                            System.out.println("Write a new patronymic: ");
                                            visitorEdit.setPatronymic(write());
                                            break;
                                        } // Patronymic

                                        case 4: {
                                            System.out.println("Write a new birthday (yyyy-mm-dd): ");
                                            visitorEdit.setBirthday(Date.valueOf(write()));
                                            break;
                                        } // Birtday

                                        case 5: {
                                            System.out.println("Write a new gender: ");
                                            visitorEdit.setGender(Integer.valueOf(write()));
                                            break;
                                        } // Gender
                                        case 6: {
                                            List<PassportEntity> passports = passportMapper.findAll();
                                            for (int i = 0; i < passports.size(); i++) {
                                                System.out.println((i + 1) + ". " + passports.get(i));
                                            }
                                            System.out.print("write a new passport id ");
                                            var passport = passports.get(Integer.valueOf(write()) - 1);
                                            visitorEdit.setPassportByPassportId(passport);
                                            break;
                                        } // passport id

                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }

                                }
                                visitorMapper.edit(visitorEdit);
                                break;
                            }  //Edit a visitor

                            case 4: {
                                var visitor = visitorMapper.findAll();
                                for (int i = 0; i < visitor.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitor.get(i));
                                }
                                System.out.print("What visitor you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                visitorMapper.delete(visitor.get(id - 1));
                                break;
                            } //Delete visitor

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    visitorFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());
                                    switch (infoKey) {
                                        case 1: {
                                            System.out.println("Write: ");
                                            var visitorFind = visitorMapper.findAllByName(write());
                                            for (VisitorEntity visitor : visitorFind) {
                                                System.out.println(visitor);
                                            }
                                            break;
                                        } // Find Surname

                                        case 2: {
                                            System.out.println("Write: ");
                                            var visitorFind = visitorMapper.findAllBySurname(write());
                                            for (VisitorEntity visitor : visitorFind) {
                                                System.out.println(visitor);
                                            }
                                            break;
                                        } // Find Name

                                        case 3: {
                                            System.out.println("Write: ");
                                            var visitorFind = visitorMapper.findAllByPatronymic(write());
                                            for (VisitorEntity visitor : visitorFind) {
                                                System.out.println(visitor);
                                            }
                                            break;
                                        } // Find patronymic

                                        case 4: {
                                            System.out.println("Write: ");
                                            var visitorFind = visitorMapper.findAllByBirthday(write());
                                            for (VisitorEntity visitor : visitorFind) {
                                                System.out.println(visitor);
                                            }
                                            break;
                                        } // Find birthday

                                        case 5: {
                                            System.out.println("Write: ");
                                            var visitorFind = visitorMapper.findAlByGender(write());
                                            for (VisitorEntity visitor : visitorFind) {
                                                System.out.println(visitor);
                                            }
                                            break;
                                        } // Find gender

                                        default: {
                                            isFind = false;
                                            break;
                                        }

                                    }
                                }
                                break;
                            } //  Find field in visitor
                            case 6:{
                                VisitorServiceEntity visitorService = new VisitorServiceEntity();

                                var services = serviceMapper.findAll();
                                for (int i = 0; i < services.size(); i++) {
                                    System.out.println((i + 1) + ". " + services.get(i));
                                }
                                System.out.print("Write specialization number: ");
                                var service = services.get(Integer.parseInt(write()) - 1);
                                visitorService.setServiceByServiceId(service);
                                //visitorService.setServiceByServiceId(service.get(Integer.parseInt(write()) - 1));


                                var visitors = visitorMapper.findAll();
                                for (int i = 0; i < visitors.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitors.get(i));
                                }
                                System.out.print("Write masters number: ");
                                var visitor = visitors.get(Integer.parseInt(write()) - 1);
                                visitorService.setVisitorByVisitorId(visitor);

                                visitorServiceMapper.save(visitorService);

                                break;
                            }//add service

                            case 7:{
                                var visitorService = visitorServiceMapper.findAll();
                                for (int i = 0; i < visitorService.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitorService.get(i));
                                }
                                System.out.print("What visitor SERVICE you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                visitorServiceMapper.delete(visitorService.get(id - 1));
                                break;
                            }//dell service

                            case 8: {
                                List<VisitorEntity> visitors = visitorMapper.findAll();
                                for (int i = 0; i < visitors.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitors.get(i));
                                }
                                System.out.print("Выберете посетителя: ");
                                int id = Integer.parseInt(write());
                                VisitorEntity visitor = visitors.get(id - 1);

                                List<VisitorServiceEntity> visitorServiceEntities = (List<VisitorServiceEntity>)visitor.getVisitorServicesById();
                                System.out.println("Услуги у посетителя: ");
                                for (int i = 0; i < visitorServiceEntities.size(); i++) {
                                    System.out.println((i + 1) + ". " + visitorServiceEntities.get(i));
                                }
                            }

                            default: {
                                insideMenu = false;
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4: { // Room
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsideRoom();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var rooms = roomMapper.findAll();
                                for (int i = 0; i < rooms.size(); i++) {
                                    System.out.println((i + 1) + ". " + rooms.get(i));
                                }
                                break;
                            } //Write Materials

                            case 2: {
                                RoomEntity rooms = new RoomEntity();
                                System.out.println("Write room number: ");
                                rooms.setRoomNumber(Integer.valueOf(write()));
                                System.out.println("Write level: ");
                                rooms.setLevel(Integer.valueOf(write()));
                                System.out.println("Write number available seats: ");
                                rooms.setNumberAvailableSeats(Integer.valueOf(write()));
                                System.out.println("Write number living people: ");
                                rooms.setLivingPeople(Integer.valueOf(write()));
                                roomMapper.save(rooms);
                                break;
                            } //Add a Rooms

                            case 3: {
                                var isEdit = true;
                                var rooms = roomMapper.findAll();
                                for (int i = 0; i < rooms.size(); i++) {
                                    System.out.println((i + 1) + ". " + rooms.get(i));
                                }
                                System.out.print("What material you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                RoomEntity roomEdit = rooms.get(id - 1);

                                while (isEdit) {
                                    roomEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());
                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write new room number: ");
                                            roomEdit.setRoomNumber(Integer.valueOf(write()));
                                            break;
                                        } // edit room number
                                        case 2: {
                                            System.out.println("Write new level: ");
                                            roomEdit.setLevel(Integer.valueOf(write()));
                                            break;
                                        } // edit level

                                        case 3: {
                                            System.out.println("Write new number available seats: ");
                                            roomEdit.setNumberAvailableSeats(Integer.valueOf(write()));
                                            break;
                                        } // edit number available seats

                                        case 4: {
                                            System.out.println("Write new living people: ");
                                            roomEdit.setLivingPeople(Integer.valueOf(write()));
                                            break;
                                        } // edit number living people

                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }
                                }
                                roomMapper.edit(roomEdit);
                                break;
                            } //Edit Room

                            case 4: {
                                var rooms = roomMapper.findAll();
                                for (int i = 0; i < rooms.size(); i++) {
                                    System.out.println((i + 1) + ". " + rooms.get(i));
                                }
                                System.out.print("What room you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                roomMapper.delete(rooms.get(id - 1));
                                break;
                            } //Delete Rooms

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    roomFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());
                                    switch (infoKey) {

                                        case 1: {
                                            System.out.println("Write: ");
                                            var roomFind = roomMapper.findAllByRoomNumber(write());
                                            for (RoomEntity rooms : roomFind) {
                                                System.out.println(rooms);
                                            }
                                            break;
                                        } //find room number

                                        case 2: {
                                            System.out.println("Write: ");
                                            var roomFind = roomMapper.findAllByLevel(write());
                                            for (RoomEntity rooms : roomFind) {
                                                System.out.println(rooms);
                                            }
                                            break;
                                        } //find level

                                        case 3: {
                                            System.out.println("Write: ");
                                            var roomFind = roomMapper.findAllByNumberAvailableSeats(write());
                                            for (RoomEntity rooms : roomFind) {
                                                System.out.println(rooms);
                                            }
                                            break;
                                        } //find number available seats

                                        case 4: {
                                            System.out.println("Write: ");
                                            var roomFind = roomMapper.findAllByLivingPeople(write());
                                            for (RoomEntity rooms : roomFind) {
                                                System.out.println(rooms);
                                            }
                                            break;
                                        } //find living people

                                        default: {
                                            isFind = false;
                                            break;
                                        }
                                    }
                                }

                                break;
                            } //Edit room

                            default: {
                                insideMenu = false;
                                break;
                            }
                        }

                    }
                    break;

                }
                case 5: { // Registration
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsideRegistration();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var registration = registrationMapper.findAll();
                                for (int i = 0; i < registration.size(); i++) {
                                    System.out.println((i + 1) + ". " + registration.get(i));
                                }
                                break;
                            } //Write registration

                            case 2: {
                                RegistrationEntity registration = new RegistrationEntity();
                                System.out.println("Write parking number: ");
                                registration.setParkingNumber(Integer.valueOf(write()));
                                System.out.println("Write car registration number: ");
                                registration.setCarRegistrationNumber(write());
                                System.out.println("Write date of entry (yyyy-mm-dd): ");
                                registration.setDateOfEntry(Date.valueOf(write()));
                                System.out.println("Write date of departure (yyyy-mm-dd): ");
                                registration.setDateOfDeparture(Date.valueOf(write()));
                                registrationMapper.save(registration);
                                break;
                            } //Add a Registration

                            case 3: {
                                var isEdit = true;
                                var registration = registrationMapper.findAll();
                                for (int i = 0; i < registration.size(); i++) {
                                    System.out.println((i + 1) + ". " + registration.get(i));
                                }
                                System.out.print("What material you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                RegistrationEntity registrationEdit = registration.get(id - 1);

                                while (isEdit) {
                                    registrationEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());
                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write new parking number: ");
                                            registrationEdit.setParkingNumber(Integer.valueOf(write()));
                                            break;
                                        } // edit parking number
                                        case 2: {
                                            System.out.println("Write new car registration number: ");
                                            registrationEdit.setCarRegistrationNumber(write());
                                            break;
                                        } // edit car registration number

                                        case 3: {
                                            System.out.println("Write new date of entry (yyyy-mm-dd): ");
                                            registrationEdit.setDateOfEntry(Date.valueOf(write()));
                                            break;
                                        } // edit date of entry

                                        case 4: {
                                            System.out.println("Write new date of departure (yyyy-mm-dd): ");
                                            registrationEdit.setDateOfDeparture(Date.valueOf(write()));
                                            break;
                                        } // edit date of departure

                                        case 5: {
                                            List<RoomEntity> rooms = roomMapper.findAll();
                                            for (int i = 0; i < rooms.size(); i++) {
                                                System.out.println((i + 1) + ". " + rooms.get(i));
                                            }
                                            System.out.print("write a new ROOM NUMBER ");
                                            var room = rooms.get(Integer.valueOf(write()) - 1);
                                            registrationEdit.setRoomByRoomNumber(room);
                                            break;
                                        } // Room number

                                        case 6: {
                                            List<VisitorEntity> visitors = visitorMapper.findAll();
                                            for (int i = 0; i < visitors.size(); i++) {
                                                System.out.println((i + 1) + ". " + visitors.get(i));
                                            }
                                            System.out.print("write a new VISITOR ID ");
                                            var visitor = visitors.get(Integer.valueOf(write()) - 1);
                                            registrationEdit.setVisitorByVisitorId(visitor);
                                            break;
                                        } // Visitor id

                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }
                                }
                                registrationMapper.edit(registrationEdit);
                                break;
                            } //Edit Registration

                            case 4: {
                                var registration = registrationMapper.findAll();
                                for (int i = 0; i < registration.size(); i++) {
                                    System.out.println((i + 1) + ". " + registration.get(i));
                                }
                                System.out.print("What material you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                registrationMapper.delete(registration.get(id - 1));
                                break;
                            } //Delete Registration

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    registrationFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());
                                    switch (infoKey) {

                                        case 1: {
                                            System.out.println("Write: ");
                                            var registrationFind = registrationMapper.findAllByParkingNumber(write());
                                            for (RegistrationEntity registration : registrationFind) {
                                                System.out.println(registration);
                                            }
                                            break;
                                        } //find parking number

                                        case 2: {
                                            System.out.println("Write: ");
                                            var registrationFind = registrationMapper.findAllByCarRegistrationNumber(write());
                                            for (RegistrationEntity registration : registrationFind) {
                                                System.out.println(registration);
                                            }
                                            break;
                                        } //find car registration number

                                        case 3: {
                                            System.out.println("Write (yyyy-mm-dd): ");
                                            var registrationFind = registrationMapper.findAllByDateOfEntry(write());
                                            for (RegistrationEntity registration : registrationFind) {
                                                System.out.println(registration);
                                            }
                                            break;
                                        } //find date of entry

                                        case 4: {
                                            System.out.println("Write (yyyy-mm-dd): ");
                                            var registrationFind = registrationMapper.findAlByDateOfDeparture(write());
                                            for (RegistrationEntity registration : registrationFind) {
                                                System.out.println(registration);
                                            }
                                            break;
                                        } //find date of departure

                                        default: {
                                            isFind = false;
                                            break;
                                        }
                                    }
                                }

                                break;
                            } //Edit material

                            default: {
                                insideMenu = false;
                                break;
                            }
                        }

                    }
                    break;

                }
                case 6: { // Service
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsideService();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var service = serviceMapper.findAll();
                                for (int i = 0; i < service.size(); i++) {
                                    System.out.println((i + 1) + ". " + service.get(i));
                                }
                                break;
                            } //Write service

                            case 2: {
                                ServiceEntity service = new ServiceEntity();
                                System.out.println("Write Name: ");
                                service.setName(write());
                                System.out.println("Write Price: ");
                                service.setPrice(Integer.valueOf(write()));
                                serviceMapper.save(service);

                                break;
                            } //Add a service

                            case 3: {
                                var isEdit = true;
                                var service = serviceMapper.findAll();
                                for (int i = 0; i < service.size(); i++) {
                                    System.out.println((i + 1) + ". " + service.get(i));
                                }
                                System.out.print("What service you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                ServiceEntity serviceEdit = service.get(id - 1);
                                while (isEdit) {
                                    serviceEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());
                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write new name: ");
                                            serviceEdit.setName(write());
                                            break;
                                        } // edit Name
                                        case 2: {
                                            System.out.println("Write new price: ");
                                            serviceEdit.setPrice(Integer.valueOf(write()));
                                            break;
                                        } // edit Price

                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }

                                }

                                serviceMapper.edit(serviceEdit);
                                break;
                            } // Edit a service

                            case 4: {
                                var service = serviceMapper.findAll();
                                for (int i = 0; i < service.size(); i++) {
                                    System.out.println((i + 1) + ". " + service.get(i));
                                }
                                System.out.print("What moving information you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                serviceMapper.delete(service.get(id - 1));
                                break;
                            } //Delete service

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    serviceFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());

                                    switch (infoKey) {
                                        case 1: {
                                            System.out.println("Write: ");
                                            var serviceFind = serviceMapper.findAllByName(write());
                                            for (ServiceEntity service : serviceFind) {
                                                System.out.println(service);
                                            }
                                            break;
                                        } // find Name

                                        case 2: {
                                            System.out.println("Write: ");
                                            var serviceFind = serviceMapper.findAllByPrice(write());
                                            for (ServiceEntity service : serviceFind) {
                                                System.out.println(service);
                                            }

                                            break;
                                        }//find Price

                                        default: {
                                            isFind = false;
                                            break;
                                        }
                                    }
                                }
                                break;

                            } //  Find field in service
                            default: {
                                insideMenu = false;
                                break;
                            }
                        }

                    }
                    break;

                }
                case 7: { // Working hours
                    boolean insideMenu = true;
                    while (insideMenu) {
                        menuInsideStaff();
                        try {
                            insideAnswer = Integer.parseInt(write());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        switch (insideAnswer) {
                            case 1: {
                                var workingHours = workinghoursMapper.findAll();
                                for (int i = 0; i < workingHours.size(); i++) {
                                    System.out.println((i + 1) + ". " + workingHours.get(i));
                                }
                                break;
                            } //Write working hours

                            case 2: {
                                WorkinghoursEntity workingHours = new WorkinghoursEntity();
                                System.out.println("Write date start (yyyy-mm-dd): ");
                                workingHours.setDatestart(Date.valueOf(write()));
                                System.out.println("Write date end (yyyy-mm-dd): ");
                                workingHours.setDateend(Date.valueOf(write()));
                                //System.out.println("Write staff id: ");
                                //workingHours.setStaffByPassportId(write()));
                                System.out.println("Write passport id of staff: ");

                                var staff = staffMapper.findAll();
                                for (int i = 0; i < staffMapper.findAll().size(); i++) {
                                    System.out.println((i + 1) + ". " + staff.get(i).getPassportByPassportId());//Допустим
                                }
                                System.out.print("Write staffs passport id ");
                                workingHours.setStaffByPassportId(staffMapper.findAll().get(Integer.parseInt(write()) - 1));

                                workinghoursMapper.save(workingHours);
                                break;
                            } //Add a working hours

                            case 3: {

                                var workingHours = workinghoursMapper.findAll();
                                for (int i = 0; i < workingHours.size(); i++) {
                                    System.out.println((i + 1) + ". " + workingHours.get(i));
                                }
                                System.out.print("What working hours you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                WorkinghoursEntity workingHoursEdit = workingHours.get(id - 1);

                                var staffs = staffMapper.findAll();
                                for (int i = 0; i < staffs.size(); i++) {
                                    System.out.println((i + 1) + ". " + staffs.get(i).getSurname() +
                                            staffs.get(i).getName() + staffs.get(i).getPatronymic() + staffs.get(i).getPassportByPassportId());
                                }
                                System.out.print("Choose staff: ");
                                var staff = staffs.get(Integer.parseInt(write()) - 1);
                                workingHoursEdit.setStaffByPassportId(staff);

                                workinghoursMapper.edit(workingHoursEdit);
                                break;

                                //
                                //
                       /*         var isEdit = true;
                                var workingHours = workinghoursMapper.findAll();
                                for (int i = 0; i < workingHours.size(); i++) {
                                    System.out.println((i + 1) + ". " + workingHours.get(i));
                                }
                                System.out.print("What working hours you want to edit (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                WorkinghoursEntity workingHoursEdit = workingHours.get(id - 1);
                                while (isEdit) {
                                    workingHoursEdit();
                                    System.out.println("Write what are you want to edit: ");
                                    int editKey = Integer.parseInt(write());

                                    switch (editKey) {
                                        case 1: {
                                            System.out.println("Write a new date start: ");
                                            workingHoursEdit.setDatestart(Date.valueOf(write()));
                                            break;
                                        } //Date start

                                        case 2: {
                                            System.out.println("Write a new date end: ");
                                            workingHoursEdit.setDateend(Date.valueOf(write()));
                                            break;
                                        } //Date end

                                        case 3: {
                                            System.out.println("Write a new staff id: ");
                                            workingHoursEdit.setStaffByPassportId(Integer.valueOf(write()));
                                            //workingHours.setStaffByPassportId(staffMapper.findAll().get(Integer.parseInt(write()) - 1));
                                            break;
                                        } // staff id

                                        //case 4: {
                                        //   System.out.println("Write a new passport id: ");
                                        //    staffEdit.setPassportId(Integer.valueOf(write()));
                                        //    break;
                                        //} // passport id
                                        default: {
                                            isEdit = false;
                                            break;
                                        }
                                    }

                                }
                                staffMapper.edit(staffEdit);
                                break;

                        */
                            }  //Edit a working hours

                            case 4: {
                                var workingHours = workinghoursMapper.findAll();
                                for (int i = 0; i < workingHours.size(); i++) {
                                    System.out.println((i + 1) + ". " + workingHours.get(i));
                                }
                                System.out.print("What working hours you want to delete (0 to exit): ");
                                int id = Integer.parseInt(write());
                                if (id == 0)
                                    break;
                                workinghoursMapper.delete(workingHours.get(id - 1));
                                break;
                            } //Delete working hours

                            case 5: {
                                boolean isFind = true;
                                while (isFind) {
                                    staffFind();
                                    System.out.println("Write what are you want to find: ");
                                    int infoKey = Integer.parseInt(write());
                                    switch (infoKey) {
                                        case 1: {
                                            System.out.println("Write: ");
                                            var workingHoursFind = workinghoursMapper.findAllByDateStart(write());
                                            for (WorkinghoursEntity workinghours : workingHoursFind) {
                                                System.out.println(workinghours);
                                            }
                                            break;
                                        } // Find date start

                                        case 2: {
                                            System.out.println("Write: ");
                                            var workingHoursFind = workinghoursMapper.findAllByDateEnd(write());
                                            for (WorkinghoursEntity workinghours : workingHoursFind) {
                                                System.out.println(workinghours);
                                            }
                                            break;
                                        } // Find date end

                                        case 3: {
                                            System.out.println("Write: ");
                                            var workingHoursFind = workinghoursMapper.findAllByPassportId(write());
                                            for (WorkinghoursEntity workinghours : workingHoursFind) {
                                                System.out.println(workinghours);
                                            }
                                            break;
                                        } // Find staff id

                                        default: {
                                            isFind = false;
                                            break;
                                        }

                                    }
                                }


                                break;
                            } //  Find field in working hours

                            default: {
                                insideMenu = false;
                                break;
                            }
                        }

                    }
                    break;
                }


            }
        }
    }





    private void chooseMainMenu() {
        System.out.println("Hostel");
        System.out.println("----------------------");
        System.out.println("1. Passport");
        System.out.println("2. Staff");
        System.out.println("3. Visitors");
        System.out.println("4. Room");
        System.out.println("5. Registration");
        System.out.println("6. Service");
        System.out.println("7. Workinghours");
        System.out.println("0. Exit");
        System.out.println("Enter the item");
    }
    private void menuInsidePassport() {
        System.out.println("Passport");
        System.out.println("1. List all passports");
        System.out.println("2. Add a passport");
        System.out.println("3. Edit a passport");
        System.out.println("4. Delete passport");
        System.out.println("5. Find field in passport");
        System.out.println("0. Back");
    }
    private void passportEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Address");
        System.out.println("2. Number");
        System.out.println("3. Date extradition");
        System.out.println("4. Passport issuance");
        System.out.println("0. Back");
    }
    private void passportFind() {
        System.out.println("What are you want to find from?");
        System.out.println("1. Address");
        System.out.println("2. Number");
        System.out.println("3. Date extradition");
        System.out.println("4. Passport issuance");
        System.out.println("0. Back");
    }
    private void menuInsideStaff() {
        System.out.println("Staff");
        System.out.println("1. List all staffs");
        System.out.println("2. Add a staff");
        System.out.println("3. Edit a staff");
        System.out.println("4. Delete staff");
        System.out.println("5. Find field in staff");
        System.out.println("0. Back");
    }
    private void staffEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Surname");
        System.out.println("2. Name");
        System.out.println("3. Patronymic");
        System.out.println("4. Passport Id");
        System.out.println("0. Back");
    }
    private void staffFind() {
        System.out.println("What are you want to find from?");
        System.out.println("1. Surname");
        System.out.println("2. Name");
        System.out.println("3. Patronymic");
        System.out.println("4. Passport by passport Id");
        System.out.println("0. Back");
    }
    private void menuInsideVisitor() {
        System.out.println("Visitor");
        System.out.println("1. List all visitor");
        System.out.println("2. Add a visitor");
        System.out.println("3. Edit a visitor");
        System.out.println("4. Delete visitor");
        System.out.println("5. Find field in visitor");
        System.out.println("6. Add SERVICE to visitor");
        System.out.println("7. Remove SERVICE to visitor");
        System.out.println("8. Prosmotr");
        System.out.println("0. Back");
            }
            private void visitorEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Patronymic");
        System.out.println("4. Birthday");
        System.out.println("5. Gender");
        System.out.println("0. Back");
            }

            private void visitorFind() {
        System.out.println("What are you want to find from?");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Patronymic");
        System.out.println("4. Birthday");
        System.out.println("5. Gender");
        System.out.println("0. Back");
            }
    private void menuInsideRoom() {
        System.out.println("Materials");
        System.out.println("1. List all rooms");
        System.out.println("2. Add a room");
        System.out.println("3. Edit a room");
        System.out.println("4. Delete room");
        System.out.println("5. Find field in room");
        System.out.println("0. Back");
    }

    private void roomEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Room number");
        System.out.println("2. Level");
        System.out.println("3. Number available seats");
        System.out.println("4. Living people");
        System.out.println("0. Back");
    }

    private void roomFind() {
        System.out.println("What are you want to find?");
        System.out.println("1. Room number");
        System.out.println("2. Level");
        System.out.println("3. Number available seats");
        System.out.println("4. Living people");
        System.out.println("0. Back");
    }

    private void menuInsideRegistration() {
        System.out.println("Registration");
        System.out.println("1. List all registration");
        System.out.println("2. Add a registration");
        System.out.println("3. Edit a registration");
        System.out.println("4. Delete registration");
        System.out.println("5. Find field in registration");
        System.out.println("0. Back");
    }

    private void registrationEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Parking number");
        System.out.println("2. Car registration number");
        System.out.println("3. Date of entry");
        System.out.println("4. Date of departure");
        System.out.println("5. Room number");
        System.out.println("6. Visitor id");
        System.out.println("0. Back");
    }

    private void registrationFind() {
        System.out.println("What are you want to find?");
        System.out.println("1. Parking number");
        System.out.println("2. Car registration number");
        System.out.println("3. Date of entry");
        System.out.println("4. Date of departure");
        //System.out.println("5. Visitor id");
        //System.out.println("6. Room number");
        System.out.println("0. Back");
    }

    private void menuInsideService() {
        System.out.println("Service");
        System.out.println("1. List all service");
        System.out.println("2. Add a service");
        System.out.println("3. Edit a service");
        System.out.println("4. Delete service");
        System.out.println("5. Find field in service");
        System.out.println("0. Back");
    }

    private void serviceEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("0. Back");
    }

    private void serviceFind() {
        System.out.println("What are you want to find from?");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("0. Back");
    }
    private void menuInsideWorkingHours() {
        System.out.println("Working hours");
        System.out.println("1. List all working hours");
        System.out.println("2. Add a working hours");
        System.out.println("3. Edit a working hours");
        System.out.println("4. Delete working hours");
        System.out.println("5. Find field in working hours");
        System.out.println("0. Back");
    }

    private void workingHoursEdit() {
        System.out.println("What are you want to edit?");
        System.out.println("1. Date start");
        System.out.println("2. Date end");
        System.out.println("3. Staff id");
        System.out.println("0. Back");
    }

    private void workingHoursFind() {
        System.out.println("What are you want to find from?");
        System.out.println("1. Date start");
        System.out.println("2. Date end");
        System.out.println("3. Staff id");
        System.out.println("0. Back");
    }
}