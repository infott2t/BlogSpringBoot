package com.example.demo.firstinstance.controller.firstinstanceurl.domain.board;
import lombok.RequiredArgsConstructor;
import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.customer.CustomerService;
// import Service, Entity, ApiDtoForm.
import com.example.demo.domain.board.Board;
import com.example.demo.domain.board.BoardApiDto;
import com.example.demo.domain.board.BoardSearchCondition;
import com.example.demo.domain.board.BoardService;
import com.example.demo.firstinstance.controller.firstinstanceurl.form.BoardApiDtoForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class InstanceUrlBoardController {

    private final BoardService boardService;
    private final CustomerService customerService;

    @GetMapping("/administer/instanceurl/board")
    public String index(Model model, BoardSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10)Pageable pageable) throws Exception {

        Page<BoardApiDto> boards = boardService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/board/index";
    }

    @GetMapping("/administer/instanceurl/board/insert")
    public String insert(Model model, BoardSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<BoardApiDto> boards = boardService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        BoardApiDtoForm userForm = new BoardApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/board/insert";
    }

    @PostMapping("/administer/instanceurl/board/insert_2")
    public String insert_2(Model model, BoardApiDtoForm userForm){

        Board board = null;
        Customer customer = null;

        if(userForm.getCustomerId()!=null) {
            try {
                  customer = customerService.findById(userForm.getCustomerId());
            } catch (Exception e) {
                return "redirect:/administer/instanceurl/board/insert";
            }
        }



        try {
            board = new Board();
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        board.setTitle(userForm.getTitle());
        board.setContent(userForm.getContent());
        board.setIsDel(userForm.getIsDel());
            if(customer !=null){board.setCustomer(customer);}
            board.setModifiedDate(LocalDateTime.now());
            board.setCreatedDate(LocalDateTime.now());
            board.setIsDel("N");

            boardService.save(board);

        } catch (Exception e) {
        return "redirect:/administer/instanceurl/board/insert";
        }
        return "redirect:/administer/instanceurl/board/insert";}

    @GetMapping("/administer/instanceurl/board/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        Board board = null;
        try {
             board = boardService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/board/";
        }

        board.setIsDel("Y");
        boardService.save(board);


        return "redirect:/administer/instanceurl/board/";
    }

    @GetMapping("/administer/instanceurl/board/update")
    public String update(Model model, @RequestParam(value="id")Long id, BoardSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{
        Page<BoardApiDto> boards = boardService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        BoardApiDtoForm userForm = new BoardApiDtoForm();
        Board board = null;

        try {
            board = boardService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/board/insert";
        }

        userForm.setId(board.getId());
              DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        userForm.setId(board.getId());
        userForm.setTitle(board.getTitle());
        userForm.setContent(board.getContent());
        userForm.setIsDel(board.getIsDel());
        if(board.getCustomer()!=null) {
            userForm.setCustomerId(board.getCustomer().getId());
        }

        userForm.setCreatedDate(board.getCreatedDate());
        userForm.setModifiedDate(board.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/board/update";
    }

    @PostMapping("/administer/instanceurl/board/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,BoardApiDtoForm userForm, BoardSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        Board board = null;
        Customer customer = null;
        try{
            board = boardService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/board/insert";
        }

        if(userForm.getCustomerId()!=null){
            try{
                customer = customerService.findById(userForm.getCustomerId());
                board.setCustomer(customer);
            }catch(Exception e){
                return "redirect:/administer/instanceurl/board/insert";
            }
        }
        try{
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        board.setTitle(userForm.getTitle());
        board.setContent(userForm.getContent());
        board.setIsDel(userForm.getIsDel());
        board.setModifiedDate(LocalDateTime.now());

        boardService.save(board);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/board/insert";
        }





        return "redirect:/administer/instanceurl/board/insert";
    }


}
