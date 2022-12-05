package com.example.w2.dao;

import com.example.w2.domain.MemberVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
    public MemberVO getWithPassword(String mid, String mpw) throws Exception{
        // DB 와 통신시에는 VO객체를 이용
        String query = "select mid, mpw from tbl_member where mid = ? and mpw = ?";

        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,mid);
        preparedStatement.setString(2,mpw);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        memberVO = MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2)).build();

        return memberVO;
    }

    public void updateUUID(String uuid, String mid) throws Exception{

        String sql = "update tbl_member set uuid=? where mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,uuid);
        preparedStatement.setString(2,mid);

        preparedStatement.executeUpdate();
    }

    public MemberVO getUsingUUID(String uuid) throws Exception{
        String query = "select mid, mpw, mname, uuid from tbl_member where uuid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,uuid);
        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();

        MemberVO memberVO = MemberVO.builder()
                .mid(rs.getString(1))
                .mpw(rs.getString(2))
                .mname(rs.getString(3))
                .uuid(rs.getString(4))
                .build();

        return memberVO;
    }
}
