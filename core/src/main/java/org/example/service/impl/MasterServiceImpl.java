package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.MasterToMasterDtoConverter;
import org.example.dto.MasterDto;
import org.example.repository.FeedbackRepository;
import org.example.repository.MasterRepository;
import org.example.service.MasterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    private final FeedbackRepository feedbackRepository;

    private final MasterToMasterDtoConverter converter;

    @Override
    public List<MasterDto> findAll() {
        List<Object[]> ratingAndScoreForAllMasters = feedbackRepository.getRatingAndScoreForMasters();
        Map<Long, MasterStats> masterStatsMap = convertQueryResultToMap(ratingAndScoreForAllMasters);
        return masterRepository.findAllWithServices().stream()
            .map(x -> converter.convert(x,
                masterStatsMap.get(x.getId()).rating,
                masterStatsMap.get(x.getId()).count))
            .toList();
    }

    private Map<Long, MasterStats> convertQueryResultToMap(List<Object[]> objects) {
        Map<Long, MasterStats> result = new HashMap<>();
        for (Object[] o : objects) {
            MasterStats masterStats = new MasterStats((Double) o[1], (Long) o[2]);
            result.put((Long) o[0], masterStats);
        }
        return result;
    }

    private record MasterStats(Double rating, Long count) {
    }
}
