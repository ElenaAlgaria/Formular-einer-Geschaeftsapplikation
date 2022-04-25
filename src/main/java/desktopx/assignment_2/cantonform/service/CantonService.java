package desktopx.assignment_2.cantonform.service;

import java.util.List;

public interface CantonService {

    List<CantonDTO> findAll();

    CantonDTO get(long id);
}
