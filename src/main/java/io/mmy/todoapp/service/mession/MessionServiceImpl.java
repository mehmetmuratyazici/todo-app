package io.mmy.todoapp.service.mession;

import io.mmy.todoapp.model.Mession;
import io.mmy.todoapp.repo.MessionRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MessionServiceImpl implements MessionService {
    private static final Logger logger = LoggerFactory.getLogger(MessionServiceImpl.class);

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

    public JSONObject updateMession(String id) {
        JSONObject result = new JSONObject();
        Optional<Mession> _record = messionRepository.findMessionById(id);

        try{
            if(_record.isPresent()){
                _record.get().setIsComplate(!_record.get().getIsComplate());
                messionRepository.save(_record.get());
                result.put("success", true);
                result.put("message", "Status updated successfully");
            }
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message", "status update error");
        }

        return result;
    }

    public JSONObject deleteMession(String id) {
        JSONObject result = new JSONObject();
        Optional<Mession> _exist = messionRepository.findMessionById(id);

        try{
            if(_exist.isPresent()){
                messionRepository.deleteById(id);
                result.put("success", true);
                result.put("message", "Mession deleted successfully");
            }
            else {
                result.put("success", false);
                result.put("message", "Mession is not exist");
            }
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message", "Mession delete error");
        }

        return result;
    }
}
