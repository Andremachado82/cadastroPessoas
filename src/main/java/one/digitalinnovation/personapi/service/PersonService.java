package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.MessageResponseDto;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository repository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDto createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = repository.save(personToSave);
        return MessageResponseDto.builder()
                .message("Created personDTO with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> getListAll() {
        List<Person> listAllPerson = repository.findAll();

        return listAllPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
