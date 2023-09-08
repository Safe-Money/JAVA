//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sptech.SafeMoney;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {
    List<Usuario> usuarios = new ArrayList();

    public UsuarioController() {
    }

    @GetMapping({"/"})
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return this.usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(this.usuarios);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Usuario> get(@PathVariable Integer id) {
        Iterator var2 = this.usuarios.iterator();

        Usuario usuario;
        do {
            if (!var2.hasNext()) {
                return ResponseEntity.status(404).build();
            }

            usuario = (Usuario)var2.next();
        } while(!usuario.getId().equals(id));

        return ResponseEntity.status(200).body(usuario);
    }

    @PostMapping({"/"})
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
        this.usuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Usuario> alteracao(@PathVariable int id, @RequestBody Usuario user) {
        ResponseEntity<Usuario> encontrado = this.get(id);
        if (encontrado.getStatusCode().value() == 404) {
            return ResponseEntity.status(404).body(user);
        } else {
            int indice = this.usuarios.indexOf(encontrado.getBody());
            user.setId(id);
            this.usuarios.set(indice, user);
            return ResponseEntity.status(200).body(user);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean excluiu = this.usuarios.removeIf((usuario) -> {
            return usuario.getId().equals(id);
        });
        return !excluiu ? ResponseEntity.status(404).build() : ResponseEntity.status(200).build();
    }
}
