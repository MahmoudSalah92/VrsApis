package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface VrsRequestMapper {

    VrsRequestMapper INSTANCE = Mappers.getMapper(VrsRequestMapper.class);

    @Mappings({
            @Mapping(source = "startDate", target = "startDate", qualifiedByName = "localDateToString"),
            @Mapping(source = "endDate", target = "endDate", qualifiedByName = "localDateToString")
    })
    VrsRequestDto toDto(VrsRequest entity);

    @Mappings({
            @Mapping(source = "startDate", target = "startDate", qualifiedByName = "stringToLocalDate"),
            @Mapping(source = "endDate", target = "endDate", qualifiedByName = "stringToLocalDate"),
            @Mapping(target = "requestDate", expression = "java(java.time.LocalDate.now())")
    })
    VrsRequest toEntity(VrsRequestDto dto);

}
