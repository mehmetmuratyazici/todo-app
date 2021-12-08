package io.mmy.todoapp.service;

import io.mmy.todoapp.model.Mession;
import io.mmy.todoapp.repo.MessionRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessionService {
    private static final Logger logger = LoggerFactory.getLogger(MessionService.class);

    @Autowired
    private MessionRepository messionRepository;

    public JSONObject createMession(Mession mession) {
        JSONObject result = new JSONObject();

        try{
            messionRepository.save(mession);
            result.put("success", true);
            result.put("id", mession.getId());
        }catch (Exception e){
            result.put("success", false);
            logger.debug(e.getMessage());
        }
        return result;
    }

    public List<Mession> getMessions(Integer userId) {
        return messionRepository.findMessionByUserId(userId);
    }
}
