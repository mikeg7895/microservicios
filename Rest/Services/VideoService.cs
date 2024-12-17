using Microsoft.EntityFrameworkCore;
using Rest.Models;

namespace Rest.Services
{
    public class VideoService : IVideoService
    {
        private readonly DetectionContext _context;

        public VideoService(DetectionContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Video>> GetAll() => await _context.Videos.ToListAsync();

        public async Task<Video?> GetById(int id) => await _context.Videos.FindAsync(id);
    }
}
