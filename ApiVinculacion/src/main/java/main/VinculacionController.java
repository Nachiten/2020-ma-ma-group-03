package main;

import model.CriterioEjecucion;
import model.OperacionDeEgreso;
import model.OperacionDeIngreso;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class VinculacionController {

    @PostMapping("/vinculacion")
    public List<String> ejecutarCriterio(@RequestParam("egresos") List<String> egresos, @RequestParam("ingresos") List<String> ingresos, @RequestParam("criterios") List<String> criterios) {
        GsonConverter gsonConverter = new GsonConverter();
        List<OperacionDeEgreso> operacionDeEgresos = gsonConverter.convertirEgresosGsonAEgresos(egresos);
        List<OperacionDeIngreso> operacionDeIngresos = gsonConverter.convertirIngresosGsonAIngresos(ingresos);
        CriterioEjecucion criterio = gsonConverter.convertirCriteriosGsonACriterio(criterios);

        return criterio.ejecutarse(operacionDeEgresos, operacionDeIngresos);
    }
}
