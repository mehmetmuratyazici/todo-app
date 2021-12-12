package io.mmy.todoapp.service.mession;

import io.mmy.todoapp.dto.MessionDto;
import io.mmy.todoapp.model.Mession;
import org.json.JSONObject;

import java.util.List;

public interface MessionService {
    JSONObject createMession(MessionDto mession);

    List<Mession> getMessions(String username);

    JSONObject updateMession(String id);

    JSONObject deleteMession(String id);
}
