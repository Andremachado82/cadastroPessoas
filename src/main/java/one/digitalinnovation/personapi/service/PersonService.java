package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.MessageResponseDto;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
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
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> getListAll() {
        List<Person> listAllPerson = repository.findAll();

        return listAllPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO getById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    public MessageResponseDto updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = repository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDto createMessageResponse(Long id, String message) {
        return MessageResponseDto
                .builder()
                .message(message + id)
                .build();
    }
}
