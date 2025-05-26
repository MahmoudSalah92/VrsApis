package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.entity.VrsRequestPhase;
import sa.gov.alriyadh.amana.entity.VrsRequestWorkTeam;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestPhaseDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestWorkTeamDto;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface VrsRequestMapper {

    VrsRequestMapper INSTANCE = Mappers.getMapper(VrsRequestMapper.class);

    @Mappings({
            @Mapping(source = "startDate", target = "startDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "endDate", target = "endDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestDate", target = "requestDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "requestStatus", target = "requestStatus"),
            @Mapping(source = "requestNotes", target = "requestNotes")
    })
    VrsRequestDto toDto(VrsRequest entity);

    @Mappings({
            @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToLocalDate"),
            @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToLocalDate"),
            @Mapping(target = "requestDate", expression = "java(java.time.LocalDateTime.now())")
    })
    VrsRequest toEntity(VrsRequestDto dto);


    @Mappings({
            @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "localDateToString"),
    })
    VrsRequestPhaseDto toDto(VrsRequestPhase entity);

    @Mappings({
            @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "stringToLocalDate"),
    })
    VrsRequestPhase toEntity(VrsRequestPhaseDto dto);


    VrsRequestWorkTeamDto toDto(VrsRequestWorkTeam entity);

    VrsRequestWorkTeam toEntity(VrsRequestWorkTeamDto dto);

}
