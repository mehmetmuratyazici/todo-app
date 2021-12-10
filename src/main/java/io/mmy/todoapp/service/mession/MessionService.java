package io.mmy.todoapp.service.mession;

import io.mmy.todoapp.model.Mession;
import org.json.JSONObject;

import java.util.List;

public interface MessionService {
    JSONObject createMession(Mession mession);
    List<Mession> getMessions(Integer userId);
    JSONObject updateMession(String id);
    JSONObject deleteMession(String id);
}
