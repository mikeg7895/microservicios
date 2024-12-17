using Microsoft.AspNetCore.Mvc;
using Rest.Models;
using Rest.Services;

namespace Rest.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VideoController : ControllerBase
    {
        private readonly IVideoService _videoService;

        public VideoController(IVideoService videoService)
        {
            _videoService = videoService;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Video>>> GetVideos() => Ok(await _videoService.GetAll());

        [HttpGet("download/{index}")]
        public async Task<IActionResult> DownloadVideo(int index)
        {
            var videoResult = await _videoService.GetById(index);
            if (videoResult == null) return NotFound();

            var path = "C:\\Users\\mayko\\OneDrive\\Escritorio\\Uni 2023-2\\software2\\proyecto\\" + videoResult.Contenido;

            if (!System.IO.File.Exists(path)) return NotFound();

            var fileName = videoResult.Nombre;
            var fileBytes = System.IO.File.ReadAllBytes(path);
            return File(fileBytes, "video/mp4", fileName);
        }
    }
}
