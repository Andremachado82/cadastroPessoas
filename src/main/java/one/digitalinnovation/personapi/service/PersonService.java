package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.MessageResponseDto;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository repository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

//    @Autowired
//    public PersonService(PersonRepository repository) {
//        this.repository = repository;
//    }

//    @Transactional(rollbackFor = Exception.class)
    public MessageResponseDto createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = repository.save(personToSave);
        return MessageResponseDto.builder()
                .message("Created personDTO with ID " + savedPerson.getId())
                .build();
    }

//    @Transactional(rollbackFor = Exception.class)
//    public List<PersonDTO> getListpersons() {
//        List<PersonDTO> listSavedPerson = repository.findAll();
//
//        return listSavedPerson;
//    }
}
